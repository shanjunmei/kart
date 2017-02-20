package com.ffzx.service;

import com.ffzx.kart.model.Member;
import com.ffzx.kart.model.OrderInfo;
import com.ffzx.kart.vo.TicketModel;

/**
 * Created by Administrator on 2017/1/22.
 */
public interface TicketService {

    OrderInfo confirm(TicketModel ticketModel, String createCode, Member member);

}
