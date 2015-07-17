/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.service;

import cn.mypandora.system.po.BaseRole;

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
public interface BaseRoleService {
    /**
     * 新建角色
     * @param baseRole
     */
    void addRole(BaseRole baseRole);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId,Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId,Long... permissionIds);
}
