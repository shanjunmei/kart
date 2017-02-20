package com.ffzx.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.MemberInfoMapper;
import com.ffzx.kart.mapper.UserMapper;
import com.ffzx.kart.model.MemberInfo;
import com.ffzx.kart.model.MemberInfoExample;
import com.ffzx.kart.model.User;
import com.ffzx.service.MemberInfoService;
import com.ffzx.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class MemberInfoServiceImpl extends BaseServiceImpl<MemberInfo,String> implements MemberInfoService {

    @Resource
    private MemberInfoMapper mapper;

    @Override
    public MemberInfoMapper getMapper() {
        return mapper;
    }

    /**
     * 查询或保存用户信息
     * @param memberInfo
     * @return
     */
    public MemberInfo findOrSave(MemberInfo memberInfo){

        MemberInfoExample example = new MemberInfoExample();
        example.createCriteria().andIdNumberEqualTo(memberInfo.getIdNumber());
        List<MemberInfo> list = mapper.selectByExample(example);

        if(StringUtils.isEmpty(list) || list.size() <= 0){
            super.add(memberInfo);
        }else{
            return list.get(0);
        }
        return memberInfo;
    }
}
