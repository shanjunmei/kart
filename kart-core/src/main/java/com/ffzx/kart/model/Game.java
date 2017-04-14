package com.ffzx.kart.model;

import com.ffzx.orm.common.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "game")
public class Game extends BaseEntity {
    /**
     * 场次名称
     */
    private String name;

    /**
     * 单场时长
     */
    private String time;

    /**
     * 门市价格
     */
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 优惠价格
     */
    @Column(name = "preferential_price")
    private BigDecimal preferentialPrice;

    /**
     * 已参赛人数
     */
    @Column(name = "participants_number")
    private String participantsNumber;

    /**
     * 预约数量
     */
    @Column(name = "bespeak_num")
    private String bespeakNum;

    /**
     * 赛车类型:0:公共车(入门级),1:公共车(专业级),2:自备车
     */
    private String type;

    /**
     * 场次时间
     */
    @Column(name = "game_time")
    private String gameTime;

    /**
     * 场次序号
     */
    @Column(name = "game_num")
    private String gameNum;

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
     * 有效时间
     */
    @Column(name = "effective_time")
    private String effectiveTime;

    /**
     * 预定状态:0可预订 1:不可预定
     */
    @Column(name = "Predetermined_state")
    private String predeterminedState;

    /**
     * 状态:0:启用 1:禁用
     */
    private String status;

    /**
     * 获取场次名称
     *
     * @return name - 场次名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置场次名称
     *
     * @param name 场次名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取单场时长
     *
     * @return time - 单场时长
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置单场时长
     *
     * @param time 单场时长
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * 获取门市价格
     *
     * @return retail_price - 门市价格
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 设置门市价格
     *
     * @param retailPrice 门市价格
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取优惠价格
     *
     * @return preferential_price - 优惠价格
     */
    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    /**
     * 设置优惠价格
     *
     * @param preferentialPrice 优惠价格
     */
    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    /**
     * 获取已参赛人数
     *
     * @return participants_number - 已参赛人数
     */
    public String getParticipantsNumber() {
        return participantsNumber;
    }

    /**
     * 设置已参赛人数
     *
     * @param participantsNumber 已参赛人数
     */
    public void setParticipantsNumber(String participantsNumber) {
        this.participantsNumber = participantsNumber == null ? null : participantsNumber.trim();
    }

    /**
     * 获取预约数量
     *
     * @return bespeak_num - 预约数量
     */
    public String getBespeakNum() {
        return bespeakNum;
    }

    /**
     * 设置预约数量
     *
     * @param bespeakNum 预约数量
     */
    public void setBespeakNum(String bespeakNum) {
        this.bespeakNum = bespeakNum == null ? null : bespeakNum.trim();
    }

    /**
     * 获取赛车类型:0:公共车(入门级),1:公共车(专业级),2:自备车
     *
     * @return type - 赛车类型:0:公共车(入门级),1:公共车(专业级),2:自备车
     */
    public String getType() {
        return type;
    }

    /**
     * 设置赛车类型:0:公共车(入门级),1:公共车(专业级),2:自备车
     *
     * @param type 赛车类型:0:公共车(入门级),1:公共车(专业级),2:自备车
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取场次时间
     *
     * @return game_time - 场次时间
     */
    public String getGameTime() {
        return gameTime;
    }

    /**
     * 设置场次时间
     *
     * @param gameTime 场次时间
     */
    public void setGameTime(String gameTime) {
        this.gameTime = gameTime == null ? null : gameTime.trim();
    }

    /**
     * 获取场次序号
     *
     * @return game_num - 场次序号
     */
    public String getGameNum() {
        return gameNum;
    }

    /**
     * 设置场次序号
     *
     * @param gameNum 场次序号
     */
    public void setGameNum(String gameNum) {
        this.gameNum = gameNum == null ? null : gameNum.trim();
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
     * 获取有效时间
     *
     * @return effective_time - 有效时间
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * 设置有效时间
     *
     * @param effectiveTime 有效时间
     */
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime == null ? null : effectiveTime.trim();
    }

    /**
     * 获取预定状态:0可预订 1:不可预定
     *
     * @return Predetermined_state - 预定状态:0可预订 1:不可预定
     */
    public String getPredeterminedState() {
        return predeterminedState;
    }

    /**
     * 设置预定状态:0可预订 1:不可预定
     *
     * @param predeterminedState 预定状态:0可预订 1:不可预定
     */
    public void setPredeterminedState(String predeterminedState) {
        this.predeterminedState = predeterminedState == null ? null : predeterminedState.trim();
    }

    /**
     * 获取状态:0:启用 1:禁用
     *
     * @return status - 状态:0:启用 1:禁用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态:0:启用 1:禁用
     *
     * @param status 状态:0:启用 1:禁用
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}