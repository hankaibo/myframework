/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.po.BaseRole;

/**
 * 角色管理Service。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseRoleService {
    /**
     * 新建角色
     *
     * @param baseRole 角色实体
     */
    void addRole(BaseRole baseRole);

    /**
     * 删除角色
     *
     * @param roleId 角色实体id
     */
    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId        角色实体id
     * @param permissionIds 权限实体id
     */
    void correlationPermission(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId        角色实体id
     * @param permissionIds 权限实体id
     */
    void uncorrelationPermission(Long roleId, Long... permissionIds);
}
