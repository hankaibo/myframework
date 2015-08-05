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
     * 根据条件返回实体列表。
     *
     * @param sqlKey sql映射名称
     * @param params 参数
     * @return 用户列表
     */
    List<BaseUser> findListByCondition(String sqlKey, Object params);

    /**
     * 根据条件获取一个实体。
     *
     * @param sqlKey sql映射名称
     * @param params 参数
     * @return 一个用户实体
     */
    BaseUser findByCondition(String sqlKey, Object params);

    /**
     * 根据条件获取Map实体列表。
     *
     * @param sqlKey sql映射名称
     * @param params 参数
     * @return 自定义实体
     */
    <O> O findCustomByCondition(String sqlKey, Object params);

    /**
     * @param sqlKey sql映射名称
     * @param params 参数
     * @param mapKey map key的列名
     * @return Map实体
     */
    Map<String, Map<String, Object>> findMapByCondition(String sqlKey, Object params, String mapKey);

    /**
     * @param sqlKey sql映射名称
     * @param params 参数
     * @return 自定义实体列表
     */
    <O> List<O> findListCustomByCondition(String sqlKey, Object params);

    /**
     * 按条件添加实体。
     *
     * @param sqlKey sql映射名称
     * @param params 参数
     */
    void addByCondetion(String sqlKey, Object params);

    /**
     * 根据条件删除实体。
     *
     * @param sqlKey sql映射名称
     * @param params 参数
     */
    void deleteByCondition(String sqlKey, Object params);
}
