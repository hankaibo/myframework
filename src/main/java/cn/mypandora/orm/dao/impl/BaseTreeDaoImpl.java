/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dao.impl;

import cn.mypandora.orm.dao.IBaseTreeDao;
import cn.mypandora.orm.model.BaseTree;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * DAO通用操作实现。(树类型)
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Repository
public abstract class BaseTreeDaoImpl<T extends BaseTree> implements IBaseTreeDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(IBaseTreeDao.class);

    private static final String LOAD_FULL_TREE = "loadFullTree";
    private static final String LOAD_TREE_LEVEL = "loadTreeWithLevel";
    private static final String GET_DESCENDANT = "getDescendant";
    private static final String GET_CHILD = "getChild";
    private static final String GET_PARENT = "getParent";
    private static final String GET_ANCESTRY = "getAncestry";

    private static final String LFT_PLUS2 = "lftPlus2";
    private static final String RGT_PLUS2 = "rgtPlus2";
    private static final String INSERT_NODE = "insertNode";
    private static final String PARENT_RGT_PLUS2 = "parentRgtPlus2";

    private static final String LFT_MINUS2 = "lftMinus2";
    private static final String RGT_MINUS2 = "rgtMinus2";

    private static final String MOVE_UP = "moveUp";
    private static final String MOVE_DOWN = "moveDown";

    private static final String FIRST_NODE = "firstNode";
    private static final String LAST_NODE = "lastNode";

    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String FIND_BY_ID = "findById";

    private static final String SQL_KEY = "SQL Key <------------";
    private static final String SQL_KEY_END = "---------->";

    @Autowired
    private SqlSession sqlSession;

    /**
     * 构造sql配置文件中的key, 格式 nameSpace+'.'+sqlKey
     *
     * @param sqlKey sql语句的名称
     * @return sql语句的完整名称
     */
    String createSqlKeyName(String sqlKey) {
        String key = getNameSpace() + "." + sqlKey;
        logger.debug(SQL_KEY + key + SQL_KEY_END);
        return key;
    }

    /**
     * 查询sql配置文件命名空间
     *
     * @return sql配置文件命名空间名称
     */
    protected abstract String getNameSpace();

    /**
     * 获取整棵树（一次性全部加载，适合数据量少的情况）
     *
     * @return 整棵树
     */
    @Override
    public List<T> loadFullTree() {
        return sqlSession.selectList(createSqlKeyName(LOAD_FULL_TREE));
    }

    /**
     * 获取某一层级节点。
     *
     * @param level 节点层级。
     * @return
     */
    @Override
    public List<T> loadTreeWithLevel(int level) {
        return sqlSession.selectList(createSqlKeyName(LOAD_TREE_LEVEL),level);
    }

    /**
     * 获得本节点及下面的所有节点
     *
     * @param id 当前操作节点id
     * @return 本节点及下面的所有节点
     */
    @Override
    public List<T> getDescendant(Long id) {
        return sqlSession.selectList(createSqlKeyName(GET_DESCENDANT), id);
    }

    /**
     * 获得本节点的孩子节点
     *
     * @param id 当前操作节点id
     * @return 本节点的孩子节点
     */
    @Override
    public List<T> getChild(Long id) {
        return sqlSession.selectList(createSqlKeyName(GET_CHILD), id);
    }

    /**
     * 获得本节点的父节点
     *
     * @param id 当前操作节点id
     * @return 本节点的父节点
     */
    @Override
    public T getParent(Long id) {
        return sqlSession.selectOne(createSqlKeyName(GET_PARENT), id);
    }

    /**
     * 获得本节点的祖先节点
     *
     * @param id 当前操作节点id
     * @return 本节点的祖先节点
     */
    @Override
    public List<T> getAncestry(Long id) {
        return sqlSession.selectList(createSqlKeyName(GET_ANCESTRY), id);
    }

    /**
     * 左节点加2
     *
     * @param id
     */
    @Override
    public void lftPlus2(Long id) {
        sqlSession.update(createSqlKeyName(LFT_PLUS2), id);
    }

    /**
     * 右节点加2
     *
     * @param id
     */
    @Override
    public void rgtPlus2(Long id) {
        sqlSession.update(createSqlKeyName(RGT_PLUS2), id);
    }

    /**
     * 添加节点
     *
     * @param params 添加节点
     */
    @Override
    public void insertNode(Object params) {
        sqlSession.insert(createSqlKeyName(INSERT_NODE), params);
    }

    /**
     * 父右节点加2
     *
     * @param id 父节点
     */
    @Override
    public void parentRgtPlus2(Long id) {
        sqlSession.update(createSqlKeyName(PARENT_RGT_PLUS2), id);
    }

    /**
     * 左节点减2
     *
     * @param id
     */
    @Override
    public void lftMinus2(Long id) {
        sqlSession.update(createSqlKeyName(LFT_MINUS2), id);
    }

    /**
     * 右节点减2
     *
     * @param id
     */
    @Override
    public void rgtMinus2(Long id) {
        sqlSession.update(createSqlKeyName(RGT_MINUS2), id);
    }

    /**
     * 左右节点加2
     *
     * @param id
     */
    @Override
    public void brotherPlus2(Long id) {
        sqlSession.update(createSqlKeyName(MOVE_DOWN), id);
    }

    /**
     * 左右节点减2
     *
     * @param id
     */
    @Override
    public void brotherMinus2(Long id) {
        sqlSession.update(createSqlKeyName(MOVE_UP), id);
    }

    /**
     * 判断是否是第一个节点
     *
     * @param id 节点id
     * @return
     */
    @Override
    public boolean isFirstNode(Long id) {
        return sqlSession.selectOne(createSqlKeyName(FIRST_NODE), id) == null ? false : true;
    }

    /**
     * 判断是否是最后一个节点
     *
     * @param id 节点id
     * @return
     */
    @Override
    public boolean isLastNode(Long id) {
        return sqlSession.selectOne(createSqlKeyName(LAST_NODE), id) == null ? false : true;
    }

    /**
     * 根据id获取实体。
     *
     * @param id 实体id
     * @return 返回单个实体
     */
    @Override
    public T findById(Serializable id) {
        return sqlSession.selectOne(createSqlKeyName(FIND_BY_ID), id);
    }

    /**
     * 修改实体。
     *
     * @param t 实体
     */
    @Override
    public void update(T t) {
        sqlSession.update(createSqlKeyName(UPDATE), t);
    }

    /**
     * 删除实体。
     *
     * @param id 实体id
     */
    @Override
    public void delete(Serializable id) {
        sqlSession.delete(createSqlKeyName(DELETE), id);
    }
}
