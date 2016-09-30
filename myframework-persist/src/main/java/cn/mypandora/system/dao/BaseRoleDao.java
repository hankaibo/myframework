/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.po.BaseRole;

/**
 * 角色管理DAO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseRoleDao extends IBaseEntityDao<BaseRole> {
    /**
     * 根据条件获取实体。
     *
     * @param sqlKey sql映射语句名称
     * @param params 参数
     * @return 一个自定义实体
     */
    <O> O findCustomByCondition(String sqlKey, Object params);

    /**
     * 按条件添加实体。
     *
     * @param sqlKey sql映射语句名称
     * @param params 参数
     */
    void addByCondetion(String sqlKey, Object params);

    /**
     * 根据条件删除实体。
     *
     * @param sqlKey sql映射语句名称
     * @param params 参数
     */
    void deleteByCondition(String sqlKey, Object params);
}
