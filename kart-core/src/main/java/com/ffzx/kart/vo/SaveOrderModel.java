package com.ffzx.kart.vo;

/**
 * Created by ljj on 2017/2/8.
 */
public class SaveOrderModel {

    /**车号*/
    private String[] numbers;
    /**姓名*/
    private String[] fullNames;
    /**电话*/
    private String[] phones;
    /**身份证号*/
    private String[] idNumbers;
    /**订单编码*/
    private String orderCode;
    /**场次编码*/
    private String gameCode;
    /**购买数量*/
    private String buyCount;
    /**单价*/
    private String favorablePrice;
    /**总价*/
    private String totalPrice;

    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }

    public String[] getFullNames() {
        return fullNames;
    }

    public void setFullNames(String[] fullNames) {
        this.fullNames = fullNames;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String[] getIdNumbers() {
        return idNumbers;
    }

    public void setIdNumbers(String[] idNumbers) {
        this.idNumbers = idNumbers;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getFavorablePrice() {
        return favorablePrice;
    }

    public void setFavorablePrice(String favorablePrice) {
        this.favorablePrice = favorablePrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
