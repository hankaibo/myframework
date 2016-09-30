/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dao.impl;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.model.BaseEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DAO通用操作实现。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Repository
public abstract class BaseEntityDaoImpl<T extends BaseEntity> implements IBaseEntityDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseEntityDaoImpl.class);

    private static final String ADD = "add";
    private static final String ADD_BATCH = "addBatch";
    private static final String UPDATE = "update";
    private static final String UPDATE_BATCH = "updateBatch";
    private static final String DELETE = "delete";
    private static final String DELETE_BATCH = "deleteBatch";
    private static final String FIND_BY_ID = "findById";
    private static final String FIND_ALL = "findAll";
    private static final String FIND_BY_SQL = "cn.mypandora.dao.base.dynaSql";

    private static final String SQL_KEY = "SQL Key <-------------";
    private static final String SQL_KEY_END = "------------>";

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
     * 添加实体。
     *
     * @param t 实体
     */
    @Override
    public void add(T t) {
        sqlSession.insert(createSqlKeyName(ADD), t);
    }

    /**
     * 批量添加实体。
     *
     * @param list 实体
     */
    @Override
    public void addBatch(List<T> list) {
        sqlSession.insert(createSqlKeyName(ADD_BATCH), list);
    }

    /**
     * 添加自定义实体。
     *
     * @param sqlKey sql语句名称
     * @param o      自定义实体
     * @param <O>    自定义实体
     */
    public <O> void addCustom(String sqlKey, O o) {
        sqlSession.insert(createSqlKeyName(sqlKey), o);
    }

    /**
     * 按条件添加实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     */
    public void addByCondetion(String sqlKey, Object params) {
        sqlSession.insert(createSqlKeyName(sqlKey), params);
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
     * 批量修改实体。
     *
     * @param list 实体
     */
    @Override
    public void updateBatch(List<T> list) {
        sqlSession.update(createSqlKeyName(UPDATE_BATCH), list);
    }

    /**
     * 更新自定义实体。
     *
     * @param sqlKey sql语句名称
     * @param o      自定义实体
     * @param <O>    自定义实体
     */
    public <O> void updateCustom(String sqlKey, O o) {
        sqlSession.update(createSqlKeyName(sqlKey), o);
    }

    /**
     * 按条件修改实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     */
    public void updateByCondition(String sqlKey, Object params) {
        sqlSession.update(createSqlKeyName(sqlKey), params);
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

    /**
     * 批量删除实体。
     *
     * @param ids 实体id数组
     */
    @Override
    public void deleteBatch(Serializable[] ids) {
        sqlSession.delete(createSqlKeyName(DELETE_BATCH), ids);
    }

    /**
     * 根据条件删除实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     */
    public void deleteByCondition(String sqlKey, Object params) {
        sqlSession.delete(createSqlKeyName(sqlKey), params);
    }

    /**
     * 根据id获取实体。
     *
     * @param id 实体id
     * @return 单个实体
     */
    @Override
    public T findById(Serializable id) {
        return sqlSession.selectOne(createSqlKeyName(FIND_BY_ID), id);
    }

    /**
     * 根据条件获取一个实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return 实体
     */
    public T findByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * 根据条件返回单个实体（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param <O>    自定义实体
     * @return 一个自定义实体
     */
    public <O> O findCustomByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * 返回指定列为key的map集合或对象
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param mapKey map key的列名
     * @return Map实体
     */
    public Map<String, Map<String, Object>> findMapByCondition(String sqlKey, Object params, String mapKey) {
        return sqlSession.selectMap(createSqlKeyName(sqlKey), params, mapKey);
    }

    /**
     * 查询一条记录。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return 一条Map记录
     */
    public Map<String, Object> findOneMapByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * 查询所有实体。
     *
     * @return 所有实体
     */
    @Override
    public List<T> findAll() {
        return sqlSession.selectList(createSqlKeyName(FIND_ALL));
    }

    /**
     * 根据条件返回实体列表。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return 满足条件的实体列表
     */
    public List<T> findListByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * 根据sql语句查询
     *
     * @param sqlKey sql语句名称
     * @return 所有数据
     */
    public List<Map<String, Object>> findListMapBySql(String sqlKey) {
        return sqlSession.selectList(createSqlKeyName(sqlKey));
    }

    /**
     * 返回Map列表数据
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return Map列表数据
     */
    public List<Map<String, Object>> findListMapByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * 根据条件返回实体列表（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param <O>    自定义实体列表
     * @return 自定义实体列表
     */
    public <O> List<O> findListCustomByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * 分页查询实体。
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @param page   返回实体Page
     * @return 分页实体
     */
    @Override
    public PageInfo<T> findPageByCondition(String sqlKey, Object params, PageInfo<T> page) {
        if (page == null) {
            page = new PageInfo<>();
            List<T> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setList(list);
            return page;
        }
//        int offset = (page.getPageNum() - 1) * page.getPageSize();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<T> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
        page = new PageInfo(list);
        return page;
    }

    /**
     * 根据sql语句查询
     *
     * @param sql  sql语句
     * @param page 分页类
     * @return 分页数据
     */
    public PageInfo<Map<String, Object>> findPageMapBySql(String sql, PageInfo<Map<String, Object>> page) {
        if (page == null) {
            page = new PageInfo<>();
            List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql);
            page.setList(list);
            return page;
        }
//        int offset = page.getPageNum();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql);
        page = new PageInfo<>(list);
        return page;

    }

    /**
     * 返回分页Map.
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param page   分页信息
     * @return 分页Map
     */
    public PageInfo<Map<String, Object>> findPageMapByCondition(String sqlKey, Object params, PageInfo<Map<String, Object>> page) {
        if (page == null) {
            page = new PageInfo<>();
            List<Map<String, Object>> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setList(list);
            return page;
        }
//        int offset = (page.getPageNum() - 1) * page.getPageSize();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Map<String, Object>> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
        page = new PageInfo<>(list);
        return page;
    }

    /**
     * 分页查询（用于关联查询自定义实体）
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @param page   返回实体Page
     * @param <O>    自定义分页实体
     * @return 分页自定义实体
     */
    public <O> PageInfo<O> findPageCustomByCondition(String sqlKey, Object params, PageInfo<O> page) {
        if (page == null) {
            page = new PageInfo<>();
            List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setList(list);
            return page;
        }
//        int offset = (page.getPageNum() - 1) * page.getPageSize();
//        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params, new RowBounds(offset, page.getPageSize()));
//        page.setList(list);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
        page = new PageInfo<>(list);
        return page;
    }

}
