package com.ffzx.weixin.menu;


import com.ffzx.kart.TestBase;
import com.ffzx.kart.mapper.WxMenuMapper;
import com.ffzx.kart.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class WxMenuServiceTest extends TestBase {

    @Resource
    private WxMenuMapper wxMenuMapper;

    @org.junit.Test
    public void createMenu() throws Exception {
        WxMenuExample example=new WxMenuExample();
        example.createCriteria().andIdGreaterThan("1");
        List<com.ffzx.kart.model.WxMenu>  data=wxMenuMapper.selectByExample(example);
        WxMenuService.createMenu(data);
    }

    @org.junit.Test
    public void createMenu1() throws Exception {
        WxMenuService.createMenu();
    }

}