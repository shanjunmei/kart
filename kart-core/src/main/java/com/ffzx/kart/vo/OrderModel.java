package com.ffzx.kart.vo;

import com.ffzx.kart.model.Game;
import com.ffzx.kart.model.OrderDetail;
import com.ffzx.kart.model.OrderInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */
public class OrderModel {
    private OrderInfo orderInfo;
    private List<OrderDetail> list;
    private Game game;
    /**
     * 是否已过期0：未使用；1：已过期；2：不在使用期；3：已使用
     */
    private String useState;

    public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderDetail> getList() {
        return list;
    }

    public void setList(List<OrderDetail> list) {
        this.list = list;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
