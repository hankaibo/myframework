/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;

/**
 * 权限实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class BasePermission extends BaseEntity {
    private static final long serialVersionUID = 7779139095887287122L;
    // 权限标识
    private String permission;
    // 权限描述
    private String description;
    // 是否可用,如果不可用将不会添加给用户
    private Boolean available = Boolean.FALSE;

    public BasePermission() {

    }

    public BasePermission(String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
