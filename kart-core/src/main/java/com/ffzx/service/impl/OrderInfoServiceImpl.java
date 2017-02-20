package com.ffzx.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.OrderInfoMapper;
import com.ffzx.kart.model.*;
import com.ffzx.kart.util.CodeGenerator;
import com.ffzx.kart.util.DateUtil;
import com.ffzx.kart.util.SerialCodeGenerator;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.vo.OrderModel;
import com.ffzx.kart.vo.SaveOrderModel;
import com.ffzx.service.GameService;
import com.ffzx.service.OrderDetailService;
import com.ffzx.service.OrderInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单业务实现类
 *
 * @author liujunjun
 * @version 1.0.0
 * @time：2017年1月17日 下午2:44:13
 */
@Service
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo, String> implements OrderInfoService {

    @Resource
    protected OrderInfoMapper orderInfoMapper;

    @Resource
    protected OrderDetailService orderDetailService;

    @Resource
    protected GameService gameService;

    @Resource
    private SerialCodeGenerator serialCodeGenerator;

    @Override
    public OrderInfoMapper getMapper() {
        return orderInfoMapper;
    }

    /**
     * 通过ID获取订单详情
     *
     * @param id
     * @return
     */
    public OrderModel getOrderModelById(String id) {
        OrderInfo orderInfo = this.findById(id);
        if (orderInfo == null) {
            return null;
        }
        return this.getOrderModel(orderInfo);
    }

    /**
     * 通过验证码获取订单详情
     *
     * @param verificationCode 验证码
     * @return
     */
    public OrderModel getOrderModelByVerificationCode(String verificationCode) {
        if (StringUtils.isEmpty(verificationCode)) {
            return null;
        }
        OrderInfoExample example = new OrderInfoExample();
        //使用状态为未支付；开始时间大于当前时间；支付状态为已支付
        example.createCriteria().andVerificationCodeEqualTo(verificationCode)
                .andUseStateEqualTo("0").andStartTimeGreaterThan(new Date())
                .andStatusEqualTo("1");
        List<OrderInfo> list = getMapper().selectByExample(example);

        if (list == null || list.size() == 0) {
            return null;
        }
        return this.getOrderModel(list.get(0));
    }

    /**
     * 获取订单详情
     *
     * @return
     */
    public OrderModel getOrderModel(OrderInfo orderInfo) {
        OrderModel orderModel = new OrderModel();
        //获取订单信息
        orderModel.setOrderInfo(orderInfo);

        //获取订单明细
        OrderDetailExample example = new OrderDetailExample();
        example.createCriteria().andOrderCodeEqualTo(orderInfo.getCode());
        orderModel.setList(orderDetailService.selectByExample(example));

        //获取场次信息
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andCodeEqualTo(orderInfo.getGameCode());
        List<Game> list = gameService.selectByExample(gameExample);
        if (list != null && list.size() > 0) {
            orderModel.setGame(list.get(0));

            //是否过期0：未使用；1：已过期；2：不在使用期；3：已使用；4：已验证
            Date newDate = new Date();
            if (orderModel.getOrderInfo().getUseState().equals("1")) {
                if (newDate.before(orderModel.getGame().getStartTime())) {
                    orderModel.setUseState("4");
                } else {
                    orderModel.setUseState("3");
                }
            } else if (newDate.before(orderModel.getGame().getStartTime())) {//未使用
                orderModel.setUseState("0");  //默认当天未使用
                //不在当天使用期
                if (DateUtil.getDateNum(DateUtil.formatDate(orderModel.getGame().getStartTime()), DateUtil.formatDate(newDate)) != 0) {
                    orderModel.setUseState("2");
                }
            } else if (orderModel.getGame().getStartTime().before(newDate)) {
                orderModel.setUseState("1");
            }
        }
        return orderModel;
    }

    /**
     * 线下购买或使用线上购买的订单
     *
     * @param model
     * @return 1 "找不到场次信息，请重新操作！";
     * 2 "订单信息有误，请重新操作！";
     * 3 "参赛人信息不全，请重新操作！";
     * 4 + number "本场次购买数量剩余number";
     */
    public synchronized int saveOrUpdate(SaveOrderModel model, User user) {
        Date date = new Date();
        //验证场次信息
        if (StringUtils.isEmpty(model.getGameCode())) {
            return 1;    //场次信息不正确
        }
        Game game = gameService.findByCode(model.getGameCode());
        int number = Integer.parseInt(game.getBespeakNum()) - Integer.parseInt(game.getParticipantsNumber());
        if (number < Integer.parseInt(model.getBuyCount())) {
            return 4 + number;        //本场次购买数量剩余number
        }

        //订单信息
        OrderInfo orderInfo = null;
        if (StringUtils.isEmpty(model.getOrderCode())) {
            orderInfo = new OrderInfo();
            orderInfo.setTotalPrice(model.getTotalPrice());
            orderInfo.setFavorablePrice(model.getFavorablePrice());
            orderInfo.setBuyCount(model.getBuyCount());
            orderInfo.setGameCode(model.getGameCode());

            orderInfo.setMemberPhone(model.getPhones()[0]);
            orderInfo.setStatus("1");
            orderInfo.setUseState("1");
            orderInfo.setUseTime(date);
            orderInfo.setPayType("1");
            orderInfo.setPayTime(date);
            orderInfo.setFinishTime(date);
            orderInfo.setActualPrice(model.getTotalPrice());
            orderInfo.setOrderSource("1");
            orderInfo.setVerificationCode(CodeGenerator.getRandStr());
            orderInfo.setOrderName("卡丁车券");
            orderInfo.setStartTime(game.getStartTime());
            orderInfo.setEndTime(game.getEndTime());
            orderInfo.setEffectiveTime(game.getEffectiveTime());
            orderInfo.setCreateBy(user.getCode());
            orderInfo.setLastUpdateBy(user.getCode());

            int addResult = this.add(orderInfo);
            if (addResult <= 0) {
                return 2;   //新增订单信息失败
            }
            model.setOrderCode(orderInfo.getCode());
        } else {
            orderInfo = this.findByCode(model.getOrderCode());
            orderInfo.setUseState("1");
            orderInfo.setUseTime(date);
            this.update(orderInfo);
        }

        //保存订单明细信息
        int detailResult = orderDetailService.saveOrderDetils(model);
        if (detailResult != 0) {
            return detailResult;
        }

        //修改场次预约数量
        gameService.updateBespeak(orderInfo, 0);

        return 0;
    }

    @Override
    public int add(OrderInfo entity) {
        entity.setVerificationCode(verificationCode());
        entity.setCode(serialCodeGenerator.getNum("order", 13));
        return super.add(entity);
    }

    /**
     * 生成当前未生效的验证码
     *
     * @return
     */
    public String verificationCode() {
        int c = 1;
        String verificationCode = null;
        while (c > 0) {
            verificationCode = CodeGenerator.getRandStr();
            OrderInfoExample example = new OrderInfoExample();
            example.createCriteria().andVerificationCodeEqualTo(verificationCode).andUseStateEqualTo("0");
            c = countByExample(example);
        }
        return verificationCode;
    }

    /**
     * 通过订单信息获取订单明细
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public List<GameUserInfoModel> getGameUserInfoModelList(OrderInfo entity) throws Exception {
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria orderDetailCriteria = example.createCriteria();
        orderDetailCriteria.andOrderCodeEqualTo(entity.getCode());
        List<OrderDetail> orderDetailList = orderDetailService.selectByExample(example);

        List<GameUserInfoModel> gameUserInfoModelList = orderDetailService.getGameUserInfoModelList(orderDetailList);

        if (gameUserInfoModelList.size() > 0) {
            return gameUserInfoModelList;
        }
        return null;
    }

    /**
     * 退款
     *
     * @param orderInfo
     * @return
     */
    public int refund(OrderInfo orderInfo) {
        orderInfo.setStatus("2");
        int result = this.update(orderInfo);
        if (result > 0) {
            gameService.updateBespeak(orderInfo, 1);

            OrderDetailExample example = new OrderDetailExample();
            OrderDetailExample.Criteria orderDetailCriteria = example.createCriteria();
            orderDetailCriteria.andOrderCodeEqualTo(orderInfo.getCode());
            List<OrderDetail> orderDetailList = orderDetailService.selectByExample(example);
            if (orderDetailList != null && orderDetailList.size() > 0) {
                for (OrderDetail detail : orderDetailList) {
                    detail.setStatus("1");
                    orderDetailService.update(detail);
                }
            }
            return result;
        }
        return 0;
    }

    @Override
    public String findMaxSeq() {
        OrderInfoExample example = new OrderInfoExample();
        example.setOrderByClause("code desc");
        PageHelper.startPage(1, 1);
        PageHelper.orderBy("code desc");
        List<OrderInfo> list = selectByExample(example);

        if (list != null && list.size() > 0) {
            String code = null;
            code = list.get(0).getCode();
            code = code.substring(code.length() - 4);
            code = String.format("%04d", Integer.valueOf(code) + 1);
            return code;
        }
        return null;
    }

    @Override
    public List<OrderInfo> findNeedRefundOrder() {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andStatusEqualTo("1").andUseStateEqualTo("0").andStartTimeLessThan(new Date());//已经支付，未使用，已过期
        return getMapper().selectByExample(example);
    }

    @Override
    public void refundTask() {
        List<OrderInfo> list = findNeedRefundOrder();
        logger.info("过期自动退款任务执行开始,找到符合条件的订单 ："+list.size()+" 个");
        int fail=0;
        for (OrderInfo order : list) {
            try{
                refund(order);
            }catch (Exception e){
                fail++;
                logger.info("refund error ",e);
            }
        }
        logger.info("过期自动退款任务执行结束,成功："+(list.size()-fail)," 失败："+fail);
    }
}
