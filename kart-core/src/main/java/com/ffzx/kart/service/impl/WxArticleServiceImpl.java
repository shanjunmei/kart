package com.ffzx.kart.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.MenuMapper;
import com.ffzx.kart.mapper.WxArticleMapper;
import com.ffzx.kart.model.Menu;
import com.ffzx.kart.model.WxArticle;
import com.ffzx.kart.service.MenuService;
import com.ffzx.kart.service.WxArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxArticleServiceImpl extends BaseServiceImpl<WxArticle,String> implements WxArticleService {

    @Resource
    private WxArticleMapper mapper;

    @Override
    public WxArticleMapper getMapper() {
        return mapper;
    }
}
