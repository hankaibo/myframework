/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;

/**
 * 角色实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class BaseRole extends BaseEntity {
    private static final long serialVersionUID = 6199599081168354432L;
    // 角色名称
    private String role;
    // 角色描述
    private String description;
    // 角色实体域对象
    private Boolean available = Boolean.FALSE;

    public BaseRole() {
    }

    public BaseRole(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
