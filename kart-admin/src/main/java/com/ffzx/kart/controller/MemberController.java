package com.ffzx.kart.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.kart.model.Member;
import com.ffzx.kart.model.MemberExample;
import com.ffzx.kart.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/Member")
public class MemberController extends BaseController<Member, String, MemberExample> {

    @Resource
    private MemberService service;


    @Override
    public MemberService getService() {
        return service;
    }

}
