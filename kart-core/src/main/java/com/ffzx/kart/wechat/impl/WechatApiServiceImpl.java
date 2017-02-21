package com.ffzx.kart.wechat.impl;

import com.ffzx.kart.util.HttpClient;
import com.ffzx.kart.util.JsonConverter;
import com.ffzx.kart.wechat.WechatApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 李淼淼
 * @description
 * @date 2017年02月10日 上午11:25:59
 */
@Service
public class WechatApiServiceImpl implements WechatApiService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 发送客服消息
     *
     * @param toWxOpenId
     * @param content
     * @param token
     * @return
     */

    @Override
    public  boolean sendMsg(String toWxOpenId, String content, String token) {

        Map<String, Object> msg = new HashMap<>();
        Map<String, String> text = new HashMap<>();
        msg.put("touser", toWxOpenId);
        msg.put("msgtype", "text");
        msg.put("text", text);
        text.put("content", content);
        String payLoad = JsonConverter.toJson(msg);
        String response = HttpClient.post(MSG_URL + token, payLoad);
        Map ret = JsonConverter.from(response, Map.class);
        logger.info("",ret);
        return true;
    }

    /**
     *
     * @param toWxopenId
     * @param content
     * @return
     */
    @Override
    public  boolean sendMsg(String toWxopenId, String content) {
        return sendMsg(toWxopenId, content, getAccessToken());
    }

    public static void main(String args[]) {
        String secret = System.setProperty("wechat.secret", "aa2f47e8ab66a4f85376196047e6f629");
        String appid = System.setProperty("wechat.appid", "wx54dc8317bb37cec5");
        WechatApiServiceImpl service = new WechatApiServiceImpl();
        Map auth = service.oauth("snsapi_base");
        System.out.println(auth);
        String wxpenid = "o32Qyv-U9hCu8Ik0wJXf7w3D6ru0";// jj o32Qyv-U9hCu8Ik0wJXf7w3D6ru0
        // my o32QyvzA8VjE9LhyGxmpWDZwvJr4
        String content = "hello world";
        //String token="-03_ucAgObpx9DpuHnYDykb0fB-sRzgbVEta_9tPgoO3bHxBfNaubckZZLI1BNzvUtxyq1DApiF0XueG6MAw7Q9CCar8VFG-dvrRajHjZ3k5ytQAJdMi9IlSAHukEHL-BOCcAIAHUI";
        boolean flag=service.sendMsg(wxpenid,content);

        String token = service.getAccessToken();
        System.out.println(token);
    }

    /***
     *获取accessToken 并且缓存
     */
    public String getAccessToken() {
        String accessToken = null;
        String secret = System.getProperty("wechat.secret");
        String appid = System.getProperty("wechat.appid");
        String get_access_token_url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "appid=" + appid +
                "&secret=" + secret + "&" +
                "grant_type=client_credential";
        try {
            String res = HttpClient.get(get_access_token_url);
            Map result = null;
            //利用从HttpEntity中得到的String生成JsonObject
            result = JsonConverter.from(res, Map.class);
            long expiresTime = (int) result.get("expires_in") * 1000 - 10000;     //设置超时时间
            accessToken = (String) result.get("access_token");
        } catch (Exception e) {
            logger.info("get tocken fail", e);
        }

        return accessToken;
    }

    /***
     *微信授权
     */
    public Map<String, String> oauth(String code) {
        String secret = System.getProperty("wechat.secret");
        String appid = System.getProperty("wechat.appid");

        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + appid +
                "&secret=" + secret + "&" +
                "code=" + code + "&grant_type=authorization_code";
        String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        String openid = "";
        String accessToken = "";
        Map<String, String> map = new HashMap<String, String>();
        //获取授权access_token以及openid
        try {
            String res = HttpClient.get(get_access_token_url);
            Map result = null;
            result = JsonConverter.from(res, Map.class);
            accessToken = (String) result.get("access_token");
            openid = (String) result.get("openid");

        } catch (Exception e) {
            logger.info("auth fail", e);
        }

        //获取用户信息
        try {
            get_userinfo = get_userinfo.replace("ACCESS_TOKEN", accessToken);
            get_userinfo = get_userinfo.replace("OPENID", openid);
            String res = HttpClient.get(get_userinfo);
            Map result = null;
            //利用从HttpEntity中得到的String生成JsonObject
            result = JsonConverter.from(res, Map.class);
            map.put("openid", (String) result.get("openid"));
            map.put("nickname", (String) result.get("nickname"));
            map.put("headimgurl", (String) result.get("headimgurl"));
            accessToken = (String) result.get("access_token");
        } catch (Exception e) {
            logger.info("auth fail", e);
        }

        return map;
    }
}

