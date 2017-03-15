package com.ffzx.kart.api;


import com.ffzx.commerce.framework.model.ServiceException;
import com.ffzx.common.utils.WebUtils;
import com.ffzx.kart.model.*;
import com.ffzx.kart.pingxx.Pay;
import com.ffzx.kart.pingxx.WebHooksVerify;
import com.ffzx.kart.service.*;
import com.ffzx.kart.util.JsonConverter;
import com.ffzx.kart.vo.OrderModel;
import com.ffzx.kart.vo.TicketModel;
import com.ffzx.kart.wechat.WechatApiService;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Webhooks;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.*;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TicketService ticketService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private ActivityService activityService;

    @Resource
    private GameService gameService;

    @Resource
    private MemberService memberService;

    @Resource
    private WechatApiService wechatApiService;

    @RequestMapping("buyTicket")
    @ResponseBody
    public Object confirm(TicketModel ticketModel) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", "0");
        ret.put("msg", "购买成功");
        try {

            Member member = getLoginMember();

            OrderInfo orderInfo = ticketService.confirm(ticketModel, member.getCode(), member);
            if (orderInfo != null) {
                ret.put("data", orderInfo);
            } else {
                throw new ServiceException("购买失败");
            }
        } catch (Exception e) {
            ret.put("code", "1");
            ret.put("msg", "购买失败：" + e.getMessage());
        }
        return ret;
    }

    @RequestMapping("myOrder")
    @ResponseBody
    public List<OrderInfo> myOrder(String useState) {
        Member member = getLoginMember();
        String userCode = null;
        if (member != null) {
            userCode = member.getCode();
        }
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(userCode)) {
            criteria.andCreateByEqualTo(userCode);
        }
        if (StringUtils.isNotBlank(useState)) {
            if ("0".equals(useState)) {
                criteria.andStatusEqualTo("0");
            } else if ("1".equals(useState)) {
                criteria.andStatusEqualTo("1").andUseStateEqualTo("0");
            } else if ("2".equals(useState)) {
                criteria.andUseStateEqualTo("1");
            } else if ("3".equals(useState)) {
                criteria.andStartTimeLessThan(new Date());
            }
            // criteria.andUseStateEqualTo(useState);
        }
        example.setOrderByClause("start_time desc");
        List<OrderInfo> orderInfos = orderInfoService.selectByExample(example);
        return orderInfos;
    }


    @RequestMapping("orderInfo")
    @ResponseBody
    public Object orderInfo() {
        Member member = getLoginMember();
        String userCode = null;
        if (member != null) {
            userCode = member.getCode();
        }
        int noPay = 0;
        int noUsed = 0;
        int used = 0;
        int invalid = 0;
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andCreateByEqualTo(userCode).andStatusEqualTo("0");
        noPay = orderInfoService.countByExample(example);

        example = new OrderInfoExample();
        example.createCriteria().andCreateByEqualTo(userCode).andStatusEqualTo("1").andUseStateEqualTo("0");
        noUsed = orderInfoService.countByExample(example);

        example = new OrderInfoExample();
        example.createCriteria().andCreateByEqualTo(userCode).andUseStateEqualTo("1");
        used = orderInfoService.countByExample(example);


        example = new OrderInfoExample();
        example.createCriteria().andCreateByEqualTo(userCode).andStartTimeLessThan(new Date());
        invalid = orderInfoService.countByExample(example);

        Map<String, Integer> info = new HashMap<>();
        info.put("noPay", noPay);
        info.put("noUsed", noUsed);
        info.put("used", used);
        info.put("invalid", invalid);
        return info;
    }

    @RequestMapping("orderDetail")
    @ResponseBody
    public OrderModel orderDetail(String orderCode) {
        OrderInfo orderInfo = orderInfoService.findByCode(orderCode);
        OrderModel orderModel = orderInfoService.getOrderModel(orderInfo);
        return orderModel;
    }

    @RequestMapping("activities")
    @ResponseBody
    public List<Activity> getActivities(Activity entity) {
        List<Activity> activities = activityService.selectByEntity(entity);
        return activities;
    }

    @RequestMapping("game")
    @ResponseBody
    public Game findByCode(String gameCode) {
        return gameService.findByCode(gameCode);
    }

    @RequestMapping("games")
    @ResponseBody
    public List<Game> games(Game entity) {
        GameExample example = new GameExample();
        example.createCriteria().andEffectiveTimeEqualTo(entity.getEffectiveTime());
        example.setOrderByClause(" start_time asc");
        List<Game> games = gameService.selectByExample(example);
        return games;
    }


    /**
     * 登录
     *
     * @param entity wxOpenid 实参为获取openid前置code
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Member entity, HttpServletRequest request) {
        Map<String, Object> ret = new HashMap<>();

        String authCode = request.getParameter("authCode");
        String refer = request.getParameter("refer");
        logger.info("authCode :{}", authCode);
        logger.info("refer :{}", refer);
        String openId = null;

        Member member = null;
        boolean iscreate = true;

        if (StringUtils.isBlank(authCode)) {

            Member m = getLoginMember();
            if (m != null) {
                ret.put("loginInfo", m);
                ret.put("refer", refer);
                return ret;
            } else {
                ret.put("code", "-3");
                ret.put("msg", "身份信息为空");
                return ret;
            }
        } else {
            Map<String, String> map = wechatApiService.oauth(authCode);
            logger.info(JsonConverter.toJson(map));
            openId = map.get("openid");
            member = memberService.findByOpenId(openId);
            if (member != null) {
                iscreate = false;
            } else {
                member = new Member();
            }

            member.setWxOpenid(map.get("openid"));
            member.setWxNickName(map.get("nickname"));
            member.setWxHeadimgurl(map.get("headimgurl"));
            if (iscreate) {
                memberService.add(member);
            } else {
                memberService.updateSelective(member);
            }
        }

        member.setPassword(null);
        WebUtils.createSession();
        WebUtils.setSessionAttribute("loginMember", member);
        ret.put("loginInfo", member);
        ret.put("refer", request.getParameter("refer"));
        return ret;
    }

    /**
     * 获取当前登录会员信息
     *
     * @return
     */
    @RequestMapping("memberInfo")
    @ResponseBody
    public Member getLoginMember() {
        return WebUtils.getSessionAttribute("loginMember");
    }


    @RequestMapping("orderCharge")
    @ResponseBody
    public Object orderCharge(String orderNo) {
        OrderInfo order = orderInfoService.findByCode(orderNo);
        if (order.getStartTime().before(new Date())) {
            throw new ServiceException("订单已过期！");
        }
        Game game = gameService.findByCode(order.getGameCode());
        String bespeknum = game.getBespeakNum();
        if (StringUtils.isBlank(bespeknum)) {
            bespeknum = "0";
        }
        if ((Integer.parseInt(order.getBuyCount()) + Integer.parseInt(game.getParticipantsNumber()) > Integer.parseInt(bespeknum))) {
            throw new ServiceException("购买数量大于当前场次剩余最大可预约数！");
        }
        Member member = getLoginMember();
        String openid = "";
        if (member != null) {
            openid = member.getWxOpenid();
        }
        Charge charge = Pay.createCharge(order, "wx_pub", null, openid);
        return charge;
    }


    /**
     * pingxx 回调接收
     *
     * @param request
     * @return
     */
    @RequestMapping("recieve")
    @ResponseBody
    public Object recieve(HttpServletRequest request) {
        try {
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                logger.info(key + " : " + value);
            }
            String sign = request.getHeader("x-pingplusplus-signature");

            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String string = "";
            while ((string = reader.readLine()) != null) {
                buffer.append(string);
            }
            reader.close();
            if (!WebHooksVerify.verifyData(buffer.toString(), sign)) {
                logger.info("验证签名失败");
                return "fail";
            }
            // 解析异步通知数据
            logger.info("pingxx webhooks 回调请求输出开始");
            logger.info(buffer.toString());
            logger.info("pingxx webhooks 回调请求输出结束");
            Event event = Webhooks.eventParse(buffer.toString());
            //String id="";
            // Event event = Event.retrieve(id);

            Object obj = Webhooks.getObject(event.toString());
            if (obj instanceof Charge) {
                String orderNo = ((Charge) obj).getOrderNo();
                OrderInfo order = orderInfoService.findByCode(orderNo);
                if (order != null) {
                    if (order.getPayTime() == null) {
                        order.setPayTime(new Date());
                        order.setPayType(((Charge) obj).getChannel());
                        order.setPayOrderNo(((Charge) obj).getTransactionNo());
                        order.setStatus("1");
                        order.setChargeId(((Charge) obj).getId());
                        orderInfoService.updateSelective(order);
                        gameService.updateBespeak(order, 0);
                    }
                } else {
                    logger.info("找不到该订单：" + orderNo);
                }
            } else if (obj instanceof Refund) {
                logger.info("接受到pingxx 退款通知");
                String orderNo = ((Refund) obj).getChargeOrderNo();
                OrderInfo order = orderInfoService.findByCode(orderNo);
                if (order != null) {
                    order.setChargeBackTime(new Date());
                    orderInfoService.refund(order);
                  /*  order.setStatus("2");
                    orderInfoService.updateSelective(order);
                    gameService.updateBespeak(order,1);*/
                } else {
                    logger.info("找不到该订单：" + orderNo);
                }

            }
        } catch (Exception e) {
            logger.info("recieve error", e);
        }
        return "success";
    }


}
