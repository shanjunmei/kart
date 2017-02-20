package com.ffzx.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.OrderDetailMapper;
import com.ffzx.kart.model.MemberInfo;
import com.ffzx.kart.model.MemberInfoExample;
import com.ffzx.kart.model.OrderDetail;
import com.ffzx.kart.model.OrderInfo;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.vo.SaveOrderModel;
import com.ffzx.service.MemberInfoService;
import com.ffzx.service.OrderDetailService;
import com.ffzx.service.OrderInfoService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/6.
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, String> implements OrderDetailService{

    @Resource
    protected OrderDetailMapper orderDetailMapper;
    
    @Resource
	private OrderInfoService orderInfoService;

    @Resource
    private MemberInfoService memberInfoService;

    @Override
    public OrderDetailMapper getMapper() {
        return orderDetailMapper;
    }

    /**
     * 新增订单明细
     * @param model
     * @return
     */
    public int saveOrderDetils(SaveOrderModel model){
        String[] numbers = model.getNumbers();
        String[] fullNames = model.getFullNames();
        String[] phones = model.getPhones();
        String[] idNumbers = model.getIdNumbers();
        if(StringUtils.isEmpty(numbers) || numbers.length <=0){
            return 3; //订单明细信息有误
        }
        for(int i = 0; i < numbers.length; i++){
            //获取用户信息
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setIdNumber(idNumbers[i]);
            memberInfo.setPhone(phones[i]);
            memberInfo.setName(fullNames[i]);
            memberInfo = memberInfoService.findOrSave(memberInfo);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderCode(model.getOrderCode());
            orderDetail.setGameCode(model.getGameCode());
            orderDetail.setMemberInfoCode(memberInfo.getCode());
            orderDetail.setNumber(numbers[i]);
            orderDetail.setStatus("0");
            super.add(orderDetail);
        }
        return 0;
    }

	@Override
	public List<GameUserInfoModel> getGameUserInfoModelList(List<OrderDetail> orderDetailList) throws Exception {
		List<GameUserInfoModel> gameUserInfoModelList = new ArrayList<GameUserInfoModel>();
		if(orderDetailList!=null && orderDetailList.size()>0){
			for(OrderDetail orderDetail:orderDetailList){
				GameUserInfoModel model=new GameUserInfoModel();
				model.setCarNum(orderDetail.getNumber());//车号
				model.setOrderCode(orderDetail.getOrderCode());//订单编码 
				OrderInfo orderInfo = orderInfoService.findByCode(orderDetail.getOrderCode());
				if(orderInfo!=null){
					model.setFavorablePrice(orderInfo.getFavorablePrice());
				}
				MemberInfo  memberInfo = memberInfoService.findByCode(orderDetail.getMemberInfoCode());
				if(memberInfo!=null){
					model.setName(memberInfo.getName());
					model.setPhone(memberInfo.getPhone());
					model.setCardNum(memberInfo.getIdNumber());
				}
				gameUserInfoModelList.add(model);
			}
		}
		return gameUserInfoModelList;
	}
}
