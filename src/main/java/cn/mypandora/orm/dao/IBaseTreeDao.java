/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dao;

import cn.mypandora.orm.model.BaseTree;

import java.io.Serializable;
import java.util.List;

/**
 * DAO通用操作API(树型)。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface IBaseTreeDao<T extends BaseTree> {

    /**
     * 获取整棵树（一次性全部加载，适合数据量少的情况）
     *
     * @return 整棵树
     */
    List<T> loadFullTree();

    /**
     * 获取某一层级节点。
     *
     * @param level 节点层级
     * @return 指定层级的树
     */
    List<T> loadTreeWithLevel(int level);

    /**
     * 获得本节点及下面的所有节点
     *
     * @param id 当前操作节点id
     * @return 本节点及下面的所有节点
     */
    List<T> getDescendant(Long id);

    /**
     * 获得本节点的孩子节点
     *
     * @param id 当前操作节点id
     * @return 本节点的孩子节点
     */
    List<T> getChild(Long id);

    /**
     * 获得本节点的父节点
     *
     * @param id 当前操作节点id
     * @return 本节点的父节点
     */
    T getParent(Long id);

    /**
     * 获得本节点的祖先节点
     *
     * @param id 当前操作节点id
     * @return 本节点的祖先节点
     */
    List<T> getAncestry(Long id);

    /**
     * 左节点加2
     *
     * @param id 节点id
     */
    void lftPlus2(Long id);

    /**
     * 右节点加2
     *
     * @param id 节点id
     */
    void rgtPlus2(Long id);

    /**
     * 添加节点
     *
     * @param params 节点信息
     */
    void insertNode(Object params);

    /**
     * 父右节点加2
     *
     * @param id 节点id
     */
    void parentRgtPlus2(Long id);

    /**
     * 左节点减2
     *
     * @param id 节点id
     */
    void lftMinus2(Long id);

    /**
     * 右节点减2
     *
     * @param id 节点id
     */
    void rgtMinus2(Long id);

    /**
     * 左右节点加2
     *
     * @param id 节点id
     */
    void brotherPlus2(Long id);

    /**
     * 左右节点减2
     *
     * @param id 节点id
     */
    void brotherMinus2(Long id);

    /**
     * 判断是否是第一个节点
     *
     * @param id 节点id
     * @return 是第一个节点，返回真；反之，则假。
     */
    boolean isFirstNode(Long id);

    /**
     * 判断是否是最后一个节点
     *
     * @param id 节点id
     * @return 是最后一个节点，返回真；反之，则假。
     */
    boolean isLastNode(Long id);

    /**
     * 修改实体。
     *
     * @param t 实体
     */
    void update(T t);

    /**
     * 删除实体。
     *
     * @param id 实体id
     */
    void delete(Serializable id);

    /**
     * 根据id获取实体。
     *
     * @param id 实体id
     * @return 返回单个实体
     */
    T findById(Serializable id);

}
