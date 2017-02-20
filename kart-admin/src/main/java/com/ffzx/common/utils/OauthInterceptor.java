package com.ffzx.common.utils;

import com.ffzx.kart.model.Member;
import com.ffzx.kart.util.JsonConverter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权拦截器
 *
 * @author 柯典佑
 */
public class OauthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String WECHAT_WEB_DEBUG = System.getProperty("wechat.web.debug");

        Member member = com.ffzx.common.utils.WebUtils.getSessionAttribute("loginMember");
        //判断浏览器类型
      /*  String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        boolean isWechat = false;
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            isWechat = true;
        }

        if ("true".equals(WECHAT_WEB_DEBUG) && member == null && !isWechat) {
            //开启了网页调试
            String WECHAT_WEB_DEBUG_OPENID = System.getProperty("wechat.web.debug.openid");
            member.setWxOpenid(WECHAT_WEB_DEBUG_OPENID);

        }*/

        if (member == null) {
            String baseUrl = request.getScheme() + "://" + request.getServerName(); //服务器地址
           // String redirectUrl = URLEncoder.encode(baseUrl + "/kart-admin/Oauth.do", "utf-8");
            String redirectUrl = URLEncoder.encode(baseUrl + "/Signin.html", "utf-8");
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + System.getProperty("wechat.appid") + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            response.setContentType("text/json");
            PrintWriter out = response.getWriter();
	        Map ret=new HashMap<>();
            ret.put("code",-2);
            ret.put("url",url);
	        out.print(JsonConverter.toJson(ret));

           // response.sendRedirect(url);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}