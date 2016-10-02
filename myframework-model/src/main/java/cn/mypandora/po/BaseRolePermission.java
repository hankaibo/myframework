/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.po;

import cn.mypandora.model.BaseEntity;

import javax.validation.constraints.Min;

/**
 * 角色-权限实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class BaseRolePermission extends BaseEntity {
    // 角色ID
    @Min(value = 1)
    private Long baseRoleId;
    // 权限ID
    @Min(value = 1)
    private Long basePermissionId;

    public Long getBaseRoleId() {
        return baseRoleId;
    }

    public void setBaseRoleId(Long baseRoleId) {
        this.baseRoleId = baseRoleId;
    }

    public Long getBasePermissionId() {
        return basePermissionId;
    }

    public void setBasePermissionId(Long basePermissionId) {
        this.basePermissionId = basePermissionId;
    }
}
