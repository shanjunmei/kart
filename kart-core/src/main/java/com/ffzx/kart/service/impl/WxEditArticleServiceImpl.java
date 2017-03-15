package com.ffzx.kart.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.MenuMapper;
import com.ffzx.kart.mapper.WxEditArticleMapper;
import com.ffzx.kart.model.Menu;
import com.ffzx.kart.model.WxEditArticle;
import com.ffzx.kart.service.MenuService;
import com.ffzx.kart.service.WxEditArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxEditArticleServiceImpl extends BaseServiceImpl<WxEditArticle,String> implements WxEditArticleService {

    @Resource
    private WxEditArticleMapper mapper;

    @Override
    public WxEditArticleMapper getMapper() {
        return mapper;
    }
}
