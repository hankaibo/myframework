/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.system.dao.BaseRoleDao;
import cn.mypandora.system.po.BaseRole;
import cn.mypandora.system.service.BaseRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 角色管理Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseRoleServiceImpl implements BaseRoleService {
    @Resource
    private BaseRoleDao dao;

    /**
     * 新建角色
     *
     * @param baseRole 角色实体
     */
    @Override
    @Transactional
    public void addRole(BaseRole baseRole) {
        dao.add(baseRole);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色实体id
     */
    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        dao.delete(roleId);
    }

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId        角色实体id
     * @param permissionIds 权限实体id
     */
    @Override
    @Transactional
    public void correlationPermission(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        for (Long permissionId : permissionIds) {
            if (!exists(roleId, permissionId)) {
                params.put("roleId", roleId);
                params.put("permissionId", permissionId);
                dao.addByCondetion("correlationRolePermission", params);
            }
        }
    }

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId        角色实体id
     * @param permissionIds 权限实体id
     */
    @Override
    @Transactional
    public void uncorrelationPermission(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        for (Long permissionId : permissionIds) {
            if (exists(roleId, permissionId)) {
                params.put("roleId", roleId);
                params.put("permissionId", permissionId);
                dao.deleteByCondition("uncorrelationRolePermission", params);
            }
        }
    }

    /**
     * @param roleId       角色实体id
     * @param permissionId 权限实体id
     * @return true, 表示存在；false表示不存在
     */
    private boolean exists(Long roleId, Long permissionId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);
        return (Integer) dao.findCustomByCondition("isExistsRolePermission", params) > 0;
    }
}
