package com.ffzx.service;

import com.ffzx.common.service.BaseService;
import com.ffzx.kart.model.Member;
import com.ffzx.kart.model.User;

/**
 * Created by Administrator on 2017/1/17.
 */
public interface MemberService extends BaseService<Member,String>{


    public Member findByOpenId(String opoenid);

}
