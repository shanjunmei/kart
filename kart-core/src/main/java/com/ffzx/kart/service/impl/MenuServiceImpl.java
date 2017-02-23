package com.ffzx.kart.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.MenuMapper;
import com.ffzx.kart.model.Menu;
import com.ffzx.kart.service.MenuService;
import com.ffzx.kart.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,String> implements MenuService {

    @Resource
    private MenuMapper mapper;

    @Override
    public MenuMapper getMapper() {
        return mapper;
    }
}