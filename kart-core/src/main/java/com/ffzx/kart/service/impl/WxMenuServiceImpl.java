package com.ffzx.kart.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.MenuMapper;
import com.ffzx.kart.mapper.WxMenuMapper;
import com.ffzx.kart.model.Menu;
import com.ffzx.kart.model.WxMenu;
import com.ffzx.kart.service.MenuService;
import com.ffzx.kart.service.WxMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxMenuServiceImpl extends BaseServiceImpl<WxMenu,String> implements WxMenuService {

    @Resource
    private WxMenuMapper mapper;

    @Override
    public WxMenuMapper getMapper() {
        return mapper;
    }
}
