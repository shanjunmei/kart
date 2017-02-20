package com.ffzx.kart.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "vehicle")
public class Vehicle extends BaseEntity {
    /**
     * 车辆名称
     */
    private String name;

    /**
     * 车辆类型:0:入门级，1:专业级,2:自备车
     */
    private String type;

    /**
     * 状态:0:可用,1:不可用
     */
    private String status;

    /**
     * 获取车辆名称
     *
     * @return name - 车辆名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置车辆名称
     *
     * @param name 车辆名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取车辆类型:0:入门级，1:专业级,2:自备车
     *
     * @return type - 车辆类型:0:入门级，1:专业级,2:自备车
     */
    public String getType() {
        return type;
    }

    /**
     * 设置车辆类型:0:入门级，1:专业级,2:自备车
     *
     * @param type 车辆类型:0:入门级，1:专业级,2:自备车
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取状态:0:可用,1:不可用
     *
     * @return status - 状态:0:可用,1:不可用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态:0:可用,1:不可用
     *
     * @param status 状态:0:可用,1:不可用
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}