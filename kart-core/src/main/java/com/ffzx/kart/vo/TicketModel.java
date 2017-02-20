package com.ffzx.kart.vo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/1/22.
 */
public class TicketModel {

    /**
     *场次编码
     */
    private String gameCode;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 手机号
     */
    private String telePhone;

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }
}
