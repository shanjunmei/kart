package com.ffzx.kart.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.kart.model.MemberInfo;
import com.ffzx.kart.model.MemberInfoExample;
import com.ffzx.kart.service.MemberInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/MemberInfo")
public class MemberInfoController extends BaseController<MemberInfo, String, MemberInfoExample> {

    @Resource
    private MemberInfoService service;


    @Override
    public MemberInfoService getService() {
        return service;
    }


}
