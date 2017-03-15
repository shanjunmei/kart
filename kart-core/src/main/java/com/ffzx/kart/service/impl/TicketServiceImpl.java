package com.ffzx.kart.service.impl;

import com.ffzx.commerce.framework.model.ServiceException;
import com.ffzx.kart.model.Game;
import com.ffzx.kart.model.Member;
import com.ffzx.kart.model.OrderInfo;
import com.ffzx.kart.util.CodeGenerator;
import com.ffzx.kart.vo.TicketModel;
import com.ffzx.kart.service.GameService;
import com.ffzx.kart.service.MemberService;
import com.ffzx.kart.service.OrderInfoService;
import com.ffzx.kart.service.TicketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/22.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private GameService gameService;


    @Resource
    private MemberService memberService;

    @Override
    public OrderInfo confirm(TicketModel ticketModel, String createCode, Member member) {
        validate(ticketModel);
        String gameCode= ticketModel.getGameCode();
        String telephone=ticketModel.getTelePhone();
        Game game= gameService.findByCode(gameCode);
        BigDecimal qty=ticketModel.getQty();

        if(game==null){
            throw  new ServiceException("场次信息错误！");
        }
        if(StringUtils.isBlank(telephone)){
            throw  new ServiceException("请输入手机号码！");
        }

        if(qty==null||qty.compareTo(BigDecimal.ZERO)<=0){
            throw  new ServiceException("购买数量必须为大于0的整数");
        }
        if(game.getStartTime().before(new Date())){
            throw  new ServiceException("不能购买当前时间以前的场次！");
        }
        String bespeknum=game.getBespeakNum();
        if(StringUtils.isBlank(bespeknum)){
            bespeknum="0";
        }
        if((Integer.parseInt(qty.toString())+Integer.parseInt(game.getParticipantsNumber())>Integer.parseInt(game.getBespeakNum()))){
            throw  new ServiceException("购买数量大于当前场次剩余最大可预约数！");
        }

        OrderInfo orderInfo=new OrderInfo();




        if(member!=null){
            member.setPhone(ticketModel.getTelePhone());
            memberService.updateSelective(member);
        }

        orderInit(game, orderInfo, telephone, qty);

        orderInfo.setCreateBy(createCode);
        int ret=orderInfoService.add(orderInfo);
        if(ret>0){
            return orderInfo;
        }
        return null;
    }

    /**
     * 订单初始化
     * @param game
     * @param orderInfo
     * @param telephone
     * @param qty
     */
    protected void orderInit(Game game, OrderInfo orderInfo, String telephone, BigDecimal qty) {
        orderInfo.setBuyCount(qty.toString());
        orderInfo.setGameCode(game.getCode());
        orderInfo.setMemberPhone(telephone);
        if(StringUtils.isBlank(orderInfo.getUseState())){
            orderInfo.setUseState("0");
        }
        if(StringUtils.isBlank(orderInfo.getStatus())){
          orderInfo.setStatus("0");
        }
        if(StringUtils.isBlank(orderInfo.getOrderSource())){
            orderInfo.setOrderSource("0");
        }
        if(StringUtils.isBlank(orderInfo.getOrderName())){
            orderInfo.setOrderName(game.getName());
        }
        if(StringUtils.isBlank(orderInfo.getOrderType())){
            orderInfo.setOrderType("0");
        }
        orderInfo.setActualPrice(game.getRetailPrice().toString());
        orderInfo.setTotalPrice(qty.multiply(game.getPreferentialPrice()).toString());
        orderInfo.setFavorablePrice(game.getPreferentialPrice().toString());
        orderInfo.setStartTime(game.getStartTime());
        orderInfo.setEndTime(game.getEndTime());
        orderInfo.setEffectiveTime(game.getEffectiveTime());
        orderInfo.setVerificationCode(CodeGenerator.getRandStr());

    }

    private void validate(TicketModel ticketModel) {
        if(ticketModel==null){
            throw new ServiceException("请求参数为空！");
        }
        if(StringUtils.isBlank(ticketModel.getGameCode())){
            throw new ServiceException("场次为空！");
        }
        if(ticketModel.getQty()==null){
            throw new ServiceException("预约数量为空！");
        }
        if(StringUtils.isBlank(ticketModel.getTelePhone())){
            throw new ServiceException("预约手机号为空！");
        }

    }
}
