package com.ffzx.kart.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by 李淼淼 on 2017/2/10.
 */
public class HttpClient {


    /**
     *
     * @param method
     * @return
     * @throws IOException
     */
    protected static String execute(HttpUriRequest method) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(method);
        HttpEntity entity = response.getEntity();
        String charset = "UTF-8";
        if (entity != null) {
            charset = getContentCharSet(entity);
            // 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1
            return EntityUtils.toString(entity, charset);
        }
        return null;
    }

    /**
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        HttpGet method = new HttpGet(url);

        try {
            String resp = execute(method);
            return resp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param url
     * @param body
     * @return
     */
    public static String post(String url, String body) {
        HttpPost method = new HttpPost(url);
        method.addHeader("Content-type", "application/json; charset=utf-8");
        method.setHeader("Accept", "application/json");
        method.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
        try {
            String resp = execute(method);
            return resp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 默认编码utf -8
     * Obtains character set of the entity, if known.
     *
     * @param entity must not be null
     * @return the character set, or null if not found
     * @throws ParseException if header elements cannot be parsed
     * @throws IllegalArgumentException if entity is null
     */
    public static String getContentCharSet(final HttpEntity entity)
            throws ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        String charset = null;
        if (entity.getContentType() != null) {
            HeaderElement values[] = entity.getContentType().getElements();
            if (values.length > 0) {
                NameValuePair param = values[0].getParameterByName("charset" );
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }

        if(StringUtils.isEmpty(charset)){
            charset = "UTF-8";
        }
        return charset;
    }

}
