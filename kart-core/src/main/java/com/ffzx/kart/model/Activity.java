package com.ffzx.kart.model;

import com.ffzx.orm.common.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "activity")
public class Activity extends BaseEntity {
    /**
     * 活动名称
     */
    private String name;

    /**
     * 类型:0:产品详情,1:文章详情,2:web连接
     */
    private String type;

    /**
     * 活动URL
     */
    private String url;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 轮播banner：1是，2否
     */
    @Column(name = "`is_ banner`")
    private String isBanner;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 开始时间
     */
    @Column(name = "start_date_time")
    private Date startDateTime;

    /**
     * 结束时间
     */
    @Column(name = "end_date_time")
    private Date endDateTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 获取活动名称
     *
     * @return name - 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置活动名称
     *
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类型:0:产品详情,1:文章详情,2:web连接
     *
     * @return type - 类型:0:产品详情,1:文章详情,2:web连接
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型:0:产品详情,1:文章详情,2:web连接
     *
     * @param type 类型:0:产品详情,1:文章详情,2:web连接
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取活动URL
     *
     * @return url - 活动URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置活动URL
     *
     * @param url 活动URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取图片路径
     *
     * @return image - 图片路径
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片路径
     *
     * @param image 图片路径
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取轮播banner：1是，2否
     *
     * @return is_ banner - 轮播banner：1是，2否
     */
    public String getIsBanner() {
        return isBanner;
    }

    /**
     * 设置轮播banner：1是，2否
     *
     * @param isBanner 轮播banner：1是，2否
     */
    public void setIsBanner(String isBanner) {
        this.isBanner = isBanner == null ? null : isBanner.trim();
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取开始时间
     *
     * @return start_date_time - 开始时间
     */
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * 设置开始时间
     *
     * @param startDateTime 开始时间
     */
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_date_time - 结束时间
     */
    public Date getEndDateTime() {
        return endDateTime;
    }

    /**
     * 设置结束时间
     *
     * @param endDateTime 结束时间
     */
    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取简介
     *
     * @return introduction - 简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置简介
     *
     * @param introduction 简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}