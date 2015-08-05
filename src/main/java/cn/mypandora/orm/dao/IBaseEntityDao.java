/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dao;

import cn.mypandora.orm.model.BaseEntity;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * DAO通用操作API。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface IBaseEntityDao<T extends BaseEntity> {

    /**
     * 添加实体。
     *
     * @param t 实体
     */
    void add(T t);

    /**
     * 批量添加实体。
     *
     * @param list 实体
     */
    void addBatch(List<T> list);

    /**
     * 修改实体。
     *
     * @param t 实体
     */
    void update(T t);

    /**
     * 批量修改实体。
     *
     * @param list 实体
     */
    void updateBatch(List<T> list);

    /**
     * 删除实体。
     *
     * @param id 实体id
     */
    void delete(Serializable id);

    /**
     * 批量删除实体。
     *
     * @param ids 实体id数组
     */
    void deleteBatch(Serializable[] ids);

    /**
     * 根据id获取实体。
     *
     * @param id 实体id
     * @return 单个实体
     */
    T findById(Serializable id);

    /**
     * 查询所有实体。
     *
     * @return 所有实体
     */
    List<T> findAll();

    /**
     * 分页查询实体。
     *
     * @param sqlKey 查询sql的名称
     * @param params 查询参数
     * @param page   返回实体Page
     * @return 分页实体
     */
    PageInfo<T> findPageByCondition(String sqlKey, Object params, PageInfo<T> page);
}
