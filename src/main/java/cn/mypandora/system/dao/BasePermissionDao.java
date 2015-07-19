/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.system.po.BasePermission;

/**
 * 权限管理DAO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BasePermissionDao extends IBaseEntityDao<BasePermission> {
    /**
     * 根据条件删除实体。
     *
     * @param sqlKey
     * @param params
     */
    void deleteByCondition(String sqlKey, Object params);
}
