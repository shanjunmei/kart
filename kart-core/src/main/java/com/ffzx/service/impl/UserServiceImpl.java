package com.ffzx.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.UserMapper;
import com.ffzx.kart.model.User;
import com.ffzx.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public UserMapper getMapper() {
        return mapper;
    }
}
