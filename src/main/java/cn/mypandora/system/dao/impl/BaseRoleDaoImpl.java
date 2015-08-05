/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao.impl;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BaseRoleDao;
import cn.mypandora.system.po.BaseRole;
import org.springframework.stereotype.Repository;

/**
 * 角色管理DAO实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Repository
public class BaseRoleDaoImpl extends BaseEntityDaoImpl<BaseRole> implements BaseRoleDao {

    /**
     * 查询sql配置文件命名空间
     *
     * @return sql配置文件命名空间名称
     */
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.service.BaseRoleService";
    }
}
