package com.ffzx.kart.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {
    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 场次编码
     */
    @Column(name = "game_code")
    private String gameCode;

    /**
     * 用户ID
     */
    @Column(name = "member_info_code")
    private String memberInfoCode;

    /**
     * 车辆ID
     */
    @Column(name = "vehicle_code")
    private String vehicleCode;

    /**
     * 车号
     */
    private String number;

    /**
     * 成绩
     */
    private String time;

    /**
     * 状态
     */
    private String status;

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
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
     * 获取用户ID
     *
     * @return member_info_code - 用户ID
     */
    public String getMemberInfoCode() {
        return memberInfoCode;
    }

    /**
     * 设置用户ID
     *
     * @param memberInfoCode 用户ID
     */
    public void setMemberInfoCode(String memberInfoCode) {
        this.memberInfoCode = memberInfoCode == null ? null : memberInfoCode.trim();
    }

    /**
     * 获取车辆ID
     *
     * @return vehicle_code - 车辆ID
     */
    public String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * 设置车辆ID
     *
     * @param vehicleCode 车辆ID
     */
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode == null ? null : vehicleCode.trim();
    }

    /**
     * 获取车号
     *
     * @return number - 车号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置车号
     *
     * @param number 车号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获取成绩
     *
     * @return time - 成绩
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置成绩
     *
     * @param time 成绩
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}