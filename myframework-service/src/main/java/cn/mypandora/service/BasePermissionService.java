/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.service;

import cn.mypandora.po.BasePermission;

/**
 * 权限管理Service。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BasePermissionService {
    /**
     * 添加权限
     *
     * @param basePermission 权限
     */
    void addPermission(BasePermission basePermission);

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     */
    void deletePermission(Long permissionId);
}
