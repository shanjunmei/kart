package com.ffzx.kart.api;

import com.ffzx.kart.model.WxArticle;
import com.ffzx.kart.model.WxEditArticle;
import com.ffzx.kart.service.WxArticleService;
import com.ffzx.kart.service.WxEditArticleService;
import com.ffzx.kart.util.HttpClient;
import com.ffzx.weixin.message.ArticlePreEditService;
import com.ffzx.weixin.message.WxMessageCoreService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
@RequestMapping("/OpenApi")
public class OpenApiController {

    @Resource
    private WxEditArticleService wxEditArticleService;

    @Resource
    private WxArticleService wxArticleService;


    @Resource
    private WxMessageCoreService wxMessageCoreService;

    @RequestMapping("onWxMessage")
    @ResponseBody
    public Object onWxMessage(HttpServletRequest request) {
        try {
            return wxMessageCoreService.processRequest(request.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value="editArticle",produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object editArticle(String code){
        WxEditArticle editArticle=wxEditArticleService.findByCode(code);
        return editArticle.getContent();
    }

    @RequestMapping(value="article",produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object article(String code){
        WxArticle editArticle=wxArticleService.findByCode(code);
        return editArticle.getContent();
    }

    @RequestMapping(value="resource")
    public void getResource(String url, HttpServletResponse response){
        try{
            HttpClient.getResource(url,response.getOutputStream());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("addArticle")
    @ResponseBody
    public Object addArticle(WxArticle article){
        Document doc= Jsoup.parse(article.getContent());
        doc.title(article.getTitle());
        article.setContent(doc.toString());
        int i=wxArticleService.add(article);
        Map<String,String> ret=new HashMap<>();
        if(i>0){
            ret.put("msg","success");
            ret.put("returnStr","http://kart.ffzxnet.com/kart-admin/OpenApi/article.do?code="+article.getCode());
        }else{
            ret.put("msg","发布失败");
        }
        return ret;
    }

}
