/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.po;

import cn.mypandora.model.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 日志实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class BaseLog extends BaseEntity {
    private static final long serialVersionUID = 4687184269056883778L;
    // 名称
    @NotNull
    private String name;
    // 描述
    @Size(min=1,max=50)
    private String description;
    // IP
    @NotNull
    private String ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
