/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.service.impl;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityService;
import cn.mypandora.system.dao.BasePermissionDao;
import cn.mypandora.system.po.BasePermission;
import cn.mypandora.system.service.BasePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
@Service
public class BasePermissionServiceImpl extends AbstractBaseEntityService<BasePermission> implements BasePermissionService {
    @Resource
    private BasePermissionDao basePermissionDao;

    /**
     * 由继承子类实现真正地实体Dao.
     *
     * @return
     */
    @Override
    public IBaseEntityDao<BasePermission> getDao() {
        return basePermissionDao;
    }

    /**
     * 添加权限
     *
     * @param basePermission
     */
    @Override
    public void addPermission(BasePermission basePermission) {
        basePermissionDao.addEntity(basePermission);
    }

    /**
     * 删除权限
     *
     * @param permissionId
     */
    @Override
    public void deletePermission(Long permissionId) {
        //1.先去关联表中删除相关联的权限
        basePermissionDao.deleteByConditions("deleteCorrelation",permissionId);
        //2.删除
        basePermissionDao.deleteEntity(permissionId);
    }
}
