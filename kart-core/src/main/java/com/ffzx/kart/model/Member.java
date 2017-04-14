package com.ffzx.kart.model;

import com.ffzx.orm.common.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member")
public class Member extends BaseEntity {
    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    @Column(name = "wx_headimgurl")
    private String wxHeadimgurl;

    /**
     * 微信Id
     */
    @Column(name = "wx_openid")
    private String wxOpenid;

    /**
     * 微信开放平台unionid
     */
    @Column(name = "wx_unionid")
    private String wxUnionid;

    /**
     * 昵称
     */
    @Column(name = "wx_nick_name")
    private String wxNickName;

    /**
     * 关注时间
     */
    @Column(name = "wx_subscribe_time")
    private Date wxSubscribeTime;

    /**
     * 用户所在城市
     */
    @Column(name = "wx_city")
    private String wxCity;

    /**
     * 用户所在国家
     */
    @Column(name = "wx_country")
    private String wxCountry;

    /**
     * 用户所在省份
     */
    @Column(name = "wx_province")
    private String wxProvince;

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取头像
     *
     * @return wx_headimgurl - 头像
     */
    public String getWxHeadimgurl() {
        return wxHeadimgurl;
    }

    /**
     * 设置头像
     *
     * @param wxHeadimgurl 头像
     */
    public void setWxHeadimgurl(String wxHeadimgurl) {
        this.wxHeadimgurl = wxHeadimgurl == null ? null : wxHeadimgurl.trim();
    }

    /**
     * 获取微信Id
     *
     * @return wx_openid - 微信Id
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置微信Id
     *
     * @param wxOpenid 微信Id
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    /**
     * 获取微信开放平台unionid
     *
     * @return wx_unionid - 微信开放平台unionid
     */
    public String getWxUnionid() {
        return wxUnionid;
    }

    /**
     * 设置微信开放平台unionid
     *
     * @param wxUnionid 微信开放平台unionid
     */
    public void setWxUnionid(String wxUnionid) {
        this.wxUnionid = wxUnionid == null ? null : wxUnionid.trim();
    }

    /**
     * 获取昵称
     *
     * @return wx_nick_name - 昵称
     */
    public String getWxNickName() {
        return wxNickName;
    }

    /**
     * 设置昵称
     *
     * @param wxNickName 昵称
     */
    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName == null ? null : wxNickName.trim();
    }

    /**
     * 获取关注时间
     *
     * @return wx_subscribe_time - 关注时间
     */
    public Date getWxSubscribeTime() {
        return wxSubscribeTime;
    }

    /**
     * 设置关注时间
     *
     * @param wxSubscribeTime 关注时间
     */
    public void setWxSubscribeTime(Date wxSubscribeTime) {
        this.wxSubscribeTime = wxSubscribeTime;
    }

    /**
     * 获取用户所在城市
     *
     * @return wx_city - 用户所在城市
     */
    public String getWxCity() {
        return wxCity;
    }

    /**
     * 设置用户所在城市
     *
     * @param wxCity 用户所在城市
     */
    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    /**
     * 获取用户所在国家
     *
     * @return wx_country - 用户所在国家
     */
    public String getWxCountry() {
        return wxCountry;
    }

    /**
     * 设置用户所在国家
     *
     * @param wxCountry 用户所在国家
     */
    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry == null ? null : wxCountry.trim();
    }

    /**
     * 获取用户所在省份
     *
     * @return wx_province - 用户所在省份
     */
    public String getWxProvince() {
        return wxProvince;
    }

    /**
     * 设置用户所在省份
     *
     * @param wxProvince 用户所在省份
     */
    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince == null ? null : wxProvince.trim();
    }
}