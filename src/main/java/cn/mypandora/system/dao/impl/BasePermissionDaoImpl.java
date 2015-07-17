/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.dao.impl;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BasePermissionDao;
import cn.mypandora.system.po.BasePermission;
import org.springframework.stereotype.Repository;

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
@Repository
public class BasePermissionDaoImpl extends BaseEntityDaoImpl<BasePermission> implements BasePermissionDao {
    /**
     * 查询sql配置文件命名空间
     *
     * @return sql配置文件命名空间名称
     */
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.PermissionEntity";
    }
}
