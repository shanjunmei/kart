package com.ffzx.kart.service;

import com.ffzx.common.service.BaseService;
import com.ffzx.kart.model.Member;

/**
 * Created by Administrator on 2017/1/17.
 */
public interface MemberService extends BaseService<Member,String>{


    public Member findByOpenId(String opoenid);

    public Member findByUnionId(String unionId);

}
