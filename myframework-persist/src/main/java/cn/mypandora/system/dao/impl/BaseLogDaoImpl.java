/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BaseLogDao;
import cn.mypandora.po.BaseLog;

/**
 * 日志管理DAO实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Repository
public class BaseLogDaoImpl extends BaseEntityDaoImpl<BaseLog> implements BaseLogDao {

    /**
     * 查询sql配置文件命名空间
     *
     * @return sql配置文件命名空间名称
     */
    @Override
    protected String getNameSpace() {
        return "cn.mypandora.service.BaseLogService";
    }
}
