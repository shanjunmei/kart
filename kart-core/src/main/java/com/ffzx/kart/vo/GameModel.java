package com.ffzx.kart.vo;

import java.math.BigDecimal;

public class GameModel {

	
	private String name; //名称
	
	private String startDate; // 启用日期
	
	private String endDate; // 启用日期时间
	
	private String morningStartTime; //上午场次设置开始时间
	
	private String morningEndTime; // 上午场次设置结束时间
	
	private String afternoonStartTime; //下午场次设置开始时间
	
	private String afternoonEndTime; //下午场次设置结束时间
	
	private String nightStartTime; // 晚上场次设置开始时间
	
	private String nightEndTime; //晚上场次设置结束时间
	
	private String singleGameTime; // 单场时间
	
	private String intervalTime; // 间隔时间
	
	private int canUseStratTime; // 可用开始时间
	
	private int canUseEndTime; // 可用结束时间
	
	private BigDecimal price; // 门市价钱
	
	private BigDecimal preferentialPrice;//优惠价钱
	
	private String type; // 塞车类型
	
	private String predeterminedState; //预定状态
	
	private String participantsNumber; //参赛人数
	
	private String bespeakNum;//预约数量
	
	private String code; //编码

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMorningStartTime() {
		return morningStartTime;
	}

	public void setMorningStartTime(String morningStartTime) {
		this.morningStartTime = morningStartTime;
	}

	public String getMorningEndTime() {
		return morningEndTime;
	}

	public void setMorningEndTime(String morningEndTime) {
		this.morningEndTime = morningEndTime;
	}

	public String getAfternoonStartTime() {
		return afternoonStartTime;
	}

	public void setAfternoonStartTime(String afternoonStartTime) {
		this.afternoonStartTime = afternoonStartTime;
	}

	public String getAfternoonEndTime() {
		return afternoonEndTime;
	}

	public void setAfternoonEndTime(String afternoonEndTime) {
		this.afternoonEndTime = afternoonEndTime;
	}

	public String getNightStartTime() {
		return nightStartTime;
	}

	public void setNightStartTime(String nightStartTime) {
		this.nightStartTime = nightStartTime;
	}

	public String getNightEndTime() {
		return nightEndTime;
	}

	public void setNightEndTime(String nightEndTime) {
		this.nightEndTime = nightEndTime;
	}

	public String getSingleGameTime() {
		return singleGameTime;
	}

	public void setSingleGameTime(String singleGameTime) {
		this.singleGameTime = singleGameTime;
	}

	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}
	
	public int getCanUseStratTime() {
		return canUseStratTime;
	}

	public void setCanUseStratTime(int canUseStratTime) {
		this.canUseStratTime = canUseStratTime;
	}

	public int getCanUseEndTime() {
		return canUseEndTime;
	}

	public void setCanUseEndTime(int canUseEndTime) {
		this.canUseEndTime = canUseEndTime;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(BigDecimal preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPredeterminedState() {
		return predeterminedState;
	}

	public void setPredeterminedState(String predeterminedState) {
		this.predeterminedState = predeterminedState;
	}

	public String getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(String participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

	public String getBespeakNum() {
		return bespeakNum;
	}

	public void setBespeakNum(String bespeakNum) {
		this.bespeakNum = bespeakNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
