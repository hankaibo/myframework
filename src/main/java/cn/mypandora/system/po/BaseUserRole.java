/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;

/**
 * 部门实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class BaseUserRole extends BaseEntity {
    private Long baseUserId;
    private Long baseRoleId;

    public Long getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Long baseUserId) {
        this.baseUserId = baseUserId;
    }

    public Long getBaseRoleId() {
        return baseRoleId;
    }

    public void setBaseRoleId(Long baseRoleId) {
        this.baseRoleId = baseRoleId;
    }
}
