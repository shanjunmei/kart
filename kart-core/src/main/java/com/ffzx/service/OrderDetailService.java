package com.ffzx.service;

import java.util.List;

import com.ffzx.common.service.BaseService;
import com.ffzx.kart.model.OrderDetail;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.vo.SaveOrderModel;

/**
 * Created by Administrator on 2017/2/6.
 */
public interface OrderDetailService extends BaseService<OrderDetail,String>{

    /**
     * 新增订单明细
     * @param model
     * @return
     */
    public int saveOrderDetils(SaveOrderModel model);
    
    public List<GameUserInfoModel> getGameUserInfoModelList(List<OrderDetail> orderDetailList) throws Exception;
}
