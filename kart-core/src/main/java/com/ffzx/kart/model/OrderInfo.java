package com.ffzx.kart.model;

import com.ffzx.orm.common.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_info")
public class OrderInfo extends BaseEntity {
    /**
     * 验证码
     */
    @Column(name = "verification_code")
    private String verificationCode;

    /**
     * 订单编号,该订单唯一标示
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 订单名称
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 对应支付网关订单编号
     */
    @Column(name = "pay_order_no")
    private String payOrderNo;

    /**
     * 下单会员编码
     */
    @Column(name = "member_code")
    private String memberCode;

    /**
     * 下单会员手机号
     */
    @Column(name = "member_phone")
    private String memberPhone;

    /**
     * 订单状态(0:未支付；1:已支付；2:已退款)
     */
    private String status;

    /**
     * 支付类型
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 购买数量
     */
    @Column(name = "buy_count")
    private String buyCount;

    /**
     * 订单总金额
     */
    @Column(name = "total_price")
    private String totalPrice;

    /**
     * 优惠金额
     */
    @Column(name = "favorable_price")
    private String favorablePrice;

    /**
     * 订单支付金额
     */
    @Column(name = "actual_price")
    private String actualPrice;

    /**
     * 订单支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 订单完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 订单完成时间
     */
    @Column(name = "charge_back_time")
    private Date chargeBackTime;

    /**
     * 退单操作人
     */
    @Column(name = "charge_back_opr")
    private String chargeBackOpr;

    /**
     * 支付ID
     */
    @Column(name = "charge_id")
    private String chargeId;

    /**
     * 订单来源0：网上购买；1：门店购买
     */
    @Column(name = "order_source")
    private String orderSource;

    /**
     * 订单类型
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 场次编码
     */
    @Column(name = "game_code")
    private String gameCode;

    /**
     * 使用状态0:未可用；1:已使用；
     */
    @Column(name = "use_state")
    private String useState;

    /**
     * 使用时间
     */
    @Column(name = "use_time")
    private Date useTime;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 有效日期
     */
    @Column(name = "effective_time")
    private String effectiveTime;

    /**
     * 获取验证码
     *
     * @return verification_code - 验证码
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * 设置验证码
     *
     * @param verificationCode 验证码
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode == null ? null : verificationCode.trim();
    }

    /**
     * 获取订单编号,该订单唯一标示
     *
     * @return order_no - 订单编号,该订单唯一标示
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号,该订单唯一标示
     *
     * @param orderNo 订单编号,该订单唯一标示
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取订单名称
     *
     * @return order_name - 订单名称
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * 设置订单名称
     *
     * @param orderName 订单名称
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    /**
     * 获取对应支付网关订单编号
     *
     * @return pay_order_no - 对应支付网关订单编号
     */
    public String getPayOrderNo() {
        return payOrderNo;
    }

    /**
     * 设置对应支付网关订单编号
     *
     * @param payOrderNo 对应支付网关订单编号
     */
    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    /**
     * 获取下单会员编码
     *
     * @return member_code - 下单会员编码
     */
    public String getMemberCode() {
        return memberCode;
    }

    /**
     * 设置下单会员编码
     *
     * @param memberCode 下单会员编码
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    /**
     * 获取下单会员手机号
     *
     * @return member_phone - 下单会员手机号
     */
    public String getMemberPhone() {
        return memberPhone;
    }

    /**
     * 设置下单会员手机号
     *
     * @param memberPhone 下单会员手机号
     */
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    /**
     * 获取订单状态(0:未支付；1:已支付；2:已退款)
     *
     * @return status - 订单状态(0:未支付；1:已支付；2:已退款)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态(0:未支付；1:已支付；2:已退款)
     *
     * @param status 订单状态(0:未支付；1:已支付；2:已退款)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取支付类型
     *
     * @return pay_type - 支付类型
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付类型
     *
     * @param payType 支付类型
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 获取购买数量
     *
     * @return buy_count - 购买数量
     */
    public String getBuyCount() {
        return buyCount;
    }

    /**
     * 设置购买数量
     *
     * @param buyCount 购买数量
     */
    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount == null ? null : buyCount.trim();
    }

    /**
     * 获取订单总金额
     *
     * @return total_price - 订单总金额
     */
    public String getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置订单总金额
     *
     * @param totalPrice 订单总金额
     */
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice == null ? null : totalPrice.trim();
    }

    /**
     * 获取优惠金额
     *
     * @return favorable_price - 优惠金额
     */
    public String getFavorablePrice() {
        return favorablePrice;
    }

    /**
     * 设置优惠金额
     *
     * @param favorablePrice 优惠金额
     */
    public void setFavorablePrice(String favorablePrice) {
        this.favorablePrice = favorablePrice == null ? null : favorablePrice.trim();
    }

    /**
     * 获取订单支付金额
     *
     * @return actual_price - 订单支付金额
     */
    public String getActualPrice() {
        return actualPrice;
    }

    /**
     * 设置订单支付金额
     *
     * @param actualPrice 订单支付金额
     */
    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice == null ? null : actualPrice.trim();
    }

    /**
     * 获取订单支付时间
     *
     * @return pay_time - 订单支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置订单支付时间
     *
     * @param payTime 订单支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取订单完成时间
     *
     * @return finish_time - 订单完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置订单完成时间
     *
     * @param finishTime 订单完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取订单完成时间
     *
     * @return charge_back_time - 订单完成时间
     */
    public Date getChargeBackTime() {
        return chargeBackTime;
    }

    /**
     * 设置订单完成时间
     *
     * @param chargeBackTime 订单完成时间
     */
    public void setChargeBackTime(Date chargeBackTime) {
        this.chargeBackTime = chargeBackTime;
    }

    /**
     * 获取退单操作人
     *
     * @return charge_back_opr - 退单操作人
     */
    public String getChargeBackOpr() {
        return chargeBackOpr;
    }

    /**
     * 设置退单操作人
     *
     * @param chargeBackOpr 退单操作人
     */
    public void setChargeBackOpr(String chargeBackOpr) {
        this.chargeBackOpr = chargeBackOpr == null ? null : chargeBackOpr.trim();
    }

    /**
     * 获取支付ID
     *
     * @return charge_id - 支付ID
     */
    public String getChargeId() {
        return chargeId;
    }

    /**
     * 设置支付ID
     *
     * @param chargeId 支付ID
     */
    public void setChargeId(String chargeId) {
        this.chargeId = chargeId == null ? null : chargeId.trim();
    }

    /**
     * 获取订单来源0：网上购买；1：门店购买
     *
     * @return order_source - 订单来源0：网上购买；1：门店购买
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * 设置订单来源0：网上购买；1：门店购买
     *
     * @param orderSource 订单来源0：网上购买；1：门店购买
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    /**
     * 获取订单类型
     *
     * @return order_type - 订单类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型
     *
     * @param orderType 订单类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * 获取场次编码
     *
     * @return game_code - 场次编码
     */
    public String getGameCode() {
        return gameCode;
    }

    /**
     * 设置场次编码
     *
     * @param gameCode 场次编码
     */
    public void setGameCode(String gameCode) {
        this.gameCode = gameCode == null ? null : gameCode.trim();
    }

    /**
     * 获取使用状态0:未可用；1:已使用；
     *
     * @return use_state - 使用状态0:未可用；1:已使用；
     */
    public String getUseState() {
        return useState;
    }

    /**
     * 设置使用状态0:未可用；1:已使用；
     *
     * @param useState 使用状态0:未可用；1:已使用；
     */
    public void setUseState(String useState) {
        this.useState = useState == null ? null : useState.trim();
    }

    /**
     * 获取使用时间
     *
     * @return use_time - 使用时间
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 设置使用时间
     *
     * @param useTime 使用时间
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取有效日期
     *
     * @return effective_time - 有效日期
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * 设置有效日期
     *
     * @param effectiveTime 有效日期
     */
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime == null ? null : effectiveTime.trim();
    }
}