/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.system.dao.BasePermissionDao;
import cn.mypandora.system.po.BasePermission;
import cn.mypandora.system.service.BasePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日志管理Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BasePermissionServiceImpl implements BasePermissionService {
    @Resource
    private BasePermissionDao dao;


    /**
     * 添加权限
     *
     * @param basePermission
     */
    @Override
    public void addPermission(BasePermission basePermission) {
        dao.add(basePermission);
    }

    /**
     * 删除权限
     *
     * @param permissionId
     */
    @Override
    public void deletePermission(Long permissionId) {
        //1.先去关联表中删除相关联的权限
        dao.deleteByCondition("deleteCorrelation", permissionId);
        //2.删除
        dao.delete(permissionId);
    }
}
