package com.ffzx.service;

import com.ffzx.common.service.BaseService;
import com.ffzx.kart.model.OrderInfo;
import com.ffzx.kart.model.User;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.vo.OrderModel;
import com.ffzx.kart.vo.SaveOrderModel;

import java.util.List;

/**
 * 订单业务类
 * @author liujunjun
 * @time：2017年1月17日 下午2:43:31
 * @version 1.0.0
 */
public interface OrderInfoService extends BaseService<OrderInfo, String>{

    /**
     * 通过ID获取订单详情
     * @param id
     * @return
     */
    public OrderModel getOrderModelById(String id);

    /**
     * 通过验证码获取订单详情
     * @param verificationCode   验证码
     * @return
     */
    public OrderModel getOrderModelByVerificationCode(String verificationCode);

    /**
     * 获取订单详情
     * @return
     */
    public OrderModel getOrderModel(OrderInfo orderInfo);

    /**
     * 线下购买或使用线上购买的订单
     * @param model
     * @return
     */
    public int saveOrUpdate(SaveOrderModel model,User user);

    /**
     * 通过订单信息获取订单明细
     * @param entity
     * @return
     * @throws Exception
     */
    public List<GameUserInfoModel> getGameUserInfoModelList(OrderInfo entity) throws Exception;

    /**
     * 退款
     * @param orderInfo
     * @return
     */
    public int refund(OrderInfo orderInfo);

    /**
     * 查询当前单据最大单号
     * @return
     */
    public String findMaxSeq();

    /**
     * 查找符合退款条件的订单
     * @return
     */
    public List<OrderInfo> findNeedRefundOrder();


    public  void refundTask();

}
