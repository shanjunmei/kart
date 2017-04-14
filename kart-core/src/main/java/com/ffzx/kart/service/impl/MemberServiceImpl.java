package com.ffzx.kart.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.MemberMapper;
import com.ffzx.kart.model.Member;
import com.ffzx.kart.model.MemberExample;
import com.ffzx.kart.service.MemberService;
import com.ffzx.kart.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member,String> implements MemberService {

    @Resource
    private MemberMapper mapper;

    @Override
    public MemberMapper getMapper() {
        return mapper;
    }

    @Override
    public Member findByOpenId(String opoenid) {
        MemberExample example=new MemberExample();
        example.createCriteria().andWxOpenidEqualTo(opoenid);
        List<Member> dataList=mapper.selectByExample(example);
        if(dataList!=null&&dataList.size()>0){
            return dataList.get(0);
        }
        return null;
    }

    @Override
    public Member findByUnionId(String unionId) {
        MemberExample example=new MemberExample();
        example.createCriteria().andWxUnionidEqualTo(unionId);
        List<Member> dataList=mapper.selectByExample(example);
        if(dataList!=null&&dataList.size()>0){
            return dataList.get(0);
        }
        return null;
    }
}
