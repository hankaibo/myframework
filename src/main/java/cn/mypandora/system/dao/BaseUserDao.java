/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.system.po.BaseUser;

import java.util.List;
import java.util.Map;

/**
 * 用户管理DAO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseUserDao extends IBaseEntityDao<BaseUser> {
    /**
     * @param sqlKey
     * @param params
     * @return List<BaseUser>
     * @Title: findByCondition
     * @Description: 根据条件返回实体列表。
     */
    List<BaseUser> findListByCondition(String sqlKey, Object params);

    /**
     * @param sqlKey
     * @param params
     * @return BaseUser
     * @Title: findEntityByCondition
     * @Description: 根据条件获取一个实体。
     */
    BaseUser findByCondition(String sqlKey, Object params);

    /**
     * 根据条件获取Map实体列表。
     *
     * @param sqlKey
     * @param params
     * @return
     */
    List<Map<String, Object>> findListMapByCondition(String sqlKey, Object params);

    /**
     * @param sqlKey
     * @param params
     * @param mapKey
     * @return
     */
    Map<String, Map<String, Object>> findMapByCondition(String sqlKey, Object params, String mapKey);

    /**
     * @param sqlKey
     * @param params
     * @return
     */
    <O> List<O> findListCustomByCondition(String sqlKey, Object params);

    /**
     * 按条件添加实体。
     *
     * @param sqlKey
     * @param params
     */
    void addByCondetion(String sqlKey, Object params);

    /**
     * 根据条件删除实体。
     *
     * @param sqlKey
     * @param params
     */
    void deleteByCondition(String sqlKey, Object params);
}
