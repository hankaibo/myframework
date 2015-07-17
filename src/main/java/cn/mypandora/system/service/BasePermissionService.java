/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.service;

import cn.mypandora.system.po.BasePermission;

/**
 * Created by kaibo on 2015/7/16.
 * desc
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BasePermissionService {
    /**
     * 添加权限
     * @param basePermission
     * @return
     */
    void addPermission(BasePermission basePermission);

    /**
     * 删除权限
     * @param permissionId
     */
    void deletePermission(Long permissionId);
}
