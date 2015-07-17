/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.service.impl;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityService;
import cn.mypandora.system.dao.BaseRoleDao;
import cn.mypandora.system.po.BaseRole;
import cn.mypandora.system.service.BaseRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
public class BaseRoleServiceImpl extends AbstractBaseEntityService<BaseRole> implements BaseRoleService {
    @Resource
    private BaseRoleDao baseRoleDao;
    /**
     * 新建角色
     *
     * @param baseRole
     */
    @Override
    public void addRole(BaseRole baseRole) {
        baseRoleDao.addEntity(baseRole);
    }

    /**
     * 删除角色
     *
     * @param roleId
     */
    @Override
    public void deleteRole(Long roleId) {
        baseRoleDao.deleteEntity(roleId);
    }

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        Map<String,Object> params=new HashMap<>();
        for(Long permissionId : permissionIds) {
            if(!exists(roleId,permissionId)) {
                params.put("roleId",roleId);
                params.put("permissionId",permissionId);
                baseRoleDao.insertByCondetion("correlationRolePermission", params);
            }
        }
    }

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        Map<String,Object> params=new HashMap<>();
        for(Long permissionId : permissionIds) {
            if(exists(roleId,permissionId)) {
                params.put("roleId",roleId);
                params.put("permissionId",permissionId);
                baseRoleDao.deleteByConditions("uncorrelationRolePermission", params);
            }
        }
    }

    /**
     * 由继承子类实现真正地实体Dao.
     *
     * @return
     */
    @Override
    public IBaseEntityDao<BaseRole> getDao() {
        return baseRoleDao;
    }

    private boolean exists(Long roleId, Long permissionId) {
        Map<String,Object> params=new HashMap<>();
        params.put("roleId",roleId);
        params.put("permissionId",permissionId);
        return baseRoleDao.findMapByCondition("isExistsRolePermission",params).size()>0;
    }
}
