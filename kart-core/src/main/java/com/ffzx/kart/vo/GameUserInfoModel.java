package com.ffzx.kart.vo;

public class GameUserInfoModel {

	/**
	 * 车号
	 */
	private String carNum; 
	
	/**
	 * 玩家名称
	 */
	private String name;
	
	/**
	 * 玩家电话号码
	 */
	private String phone;
	
	/**
	 * 玩家身份证号码
	 */
	private String cardNum;
	
	/**
	 * 价格
	 */
	private String favorablePrice;
	
	/**
	 * 订单号
	 */
	private String orderCode;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getFavorablePrice() {
		return favorablePrice;
	}

	public void setFavorablePrice(String favorablePrice) {
		this.favorablePrice = favorablePrice;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
