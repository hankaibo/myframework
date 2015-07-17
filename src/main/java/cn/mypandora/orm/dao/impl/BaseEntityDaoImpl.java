/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dao.impl;

import cn.mypandora.orm.MyBatisSql;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.model.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.RowBounds;
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
    private static final String SQL_KEY = "SQL Key <";
    private static final String SQL_KEY_END = ">";

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
     * 查询sql
     *
     * @param sqlKey sql语句名称
     * @param param  参数
     * @return
     */
    public String getSql(String sqlKey, Object param) {
        String fullSqlKey = createSqlKeyName(sqlKey);
        return getMyBatisSql(fullSqlKey, param).toString();
    }

    /**
     * 根据sql语句查询
     *
     * @param sqlKey sql语句名称
     * @return 所有数据
     */
    public List<Map<String, Object>> findBySql(String sqlKey) {
        return sqlSession.selectList(createSqlKeyName(sqlKey));
    }

    /**
     * 根据sql语句查询
     *
     * @param sql  sql语句
     * @param page 分页类
     * @return 分页数据
     */
    public Page<Map<String, Object>> findMapBySql(String sql, Page<Map<String, Object>> page) {
        if (page == null) {
            page = new Page<>();
            List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql);
            page.setResultList(list);
            return page;
        }

        int offset = page.getFirst();
        List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql,
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        page.setTotal(countBySql(sql));

        return page;

    }

    /**
     * 添加实体。
     *
     * @param t 实体
     */
    @Override
    public void addEntity(T t) {
        sqlSession.insert(createSqlKeyName(ADD), t);
    }

    /**
     * 批量添加实体。
     *
     * @param list 实体
     */
    @Override
    public void addBatchEntity(List<T> list) {
        sqlSession.insert(createSqlKeyName(ADD_BATCH), list);
    }

    /**
     * 添加自定义实体。
     *
     * @param sqlKey sql语句名称
     * @param o      自定义实体
     * @param <O>    自定义实体
     */
    public <O> void addEntity(String sqlKey, O o) {
        sqlSession.insert(createSqlKeyName(sqlKey), o);
    }

    /**
     * 按条件添加实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     */
    public void insertByCondetion(String sqlKey, Object params) {
        sqlSession.insert(createSqlKeyName(sqlKey), params);
    }

    /**
     * 修改实体。
     *
     * @param t 实体
     */
    @Override
    public void updateEntity(T t) {
        sqlSession.update(createSqlKeyName(UPDATE), t);
    }

    /**
     * 批量修改实体。
     *
     * @param list 实体
     */
    @Override
    public void updateBatchEntity(List<T> list) {
        sqlSession.update(createSqlKeyName(UPDATE_BATCH), list);
    }

    /**
     * 更新自定义实体。
     *
     * @param sqlKey   sql语句名称
     * @param o        自定义实体
     * @param <O>自定义实体
     */
    public <O> void updateEntity(String sqlKey, O o) {
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
    public void deleteEntity(Serializable id) {
        sqlSession.delete(createSqlKeyName(DELETE), id);
    }

    /**
     * 批量删除实体。
     *
     * @param ids 实体id数组
     */
    @Override
    public void deleteBatchEntity(Serializable[] ids) {
        sqlSession.delete(createSqlKeyName(DELETE_BATCH), ids);
    }

    /**
     * 根据条件删除实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     */
    public void deleteByConditions(String sqlKey, Object params) {
        sqlSession.delete(createSqlKeyName(sqlKey), params);
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
     * 根据条件获取一个实体。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return 实体
     */
    public T findEntityByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * 根据条件返回单个实体（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param <O>    自定义实体
     * @return
     */
    public <O> O findObjectByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param mapKey map key的列名
     * @return Map实体
     */
    public Map<String, Map<String, Object>> findMapByCondition(String sqlKey, Object params, String mapKey) {
        return sqlSession.selectMap(createSqlKeyName(sqlKey), params, mapKey);
    }

    /**
     * 查询所有实体。
     *
     * @return 返回所有实体
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
    public List<T> findByCondition(String sqlKey, Object params) {
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
    public <O> List<O> findObjectListByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * 查询一条记录。
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return 一条Mar记录
     */
    public Map<String, Object> findOneByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * 返回Map.
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @return Map列表数据
     */
    public List<Map<String, Object>> findMapByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * 返回分页Map.
     *
     * @param sqlKey sql语句名称
     * @param params 参数
     * @param page   分页信息
     * @return 分页Map
     */
    public Page<Map<String, Object>> findMapByConditionPage(String sqlKey, Object params, Page<Map<String, Object>> page) {
        if (page == null) {
            page = new Page<>();
            List<Map<String, Object>> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<Map<String, Object>> list = sqlSession.selectList(createSqlKeyName(sqlKey), params, new RowBounds(offset,
                page.getPageSize()));
        page.setResultList(list);
        page.setTotal(count(sqlKey, params));
        return page;
    }

    /**
     * 分页查询实体。
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @param page   返回实体Page
     * @return 返回分页实体
     */
    @Override
    public Page<T> findByCondition(String sqlKey, Object params, Page<T> page) {
        if (page == null) {
            page = new Page<>();
            List<T> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<T> list = sqlSession.selectList(createSqlKeyName(sqlKey), params,
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        page.setTotal(count(sqlKey, params));
        return page;
    }

    /**
     * 分页查询（用于关联查询自定义实体）
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @param page   返回实体Page
     * @param <O>    自定义分页实体
     * @return
     */
    public <O> Page<O> findObjectPageByCondition(String sqlKey, Object params, Page<O> page) {
        if (page == null) {
            page = new Page<>();
            List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params,
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        page.setTotal(count(sqlKey, params));
        return page;
    }

    /**
     * 分页查询（传入实体，count+sqlKey 为 count一次统计key值，对数据新增操作少的查询）
     * 使用两个查询,查询数据使用sqlKey,查询统统sqlKey为 "count"+sqlkey
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @param page   返回实体Page
     * @param <O>    自定义分页实体
     * @return
     */
    public <O> Page<O> findOPageByCondition(String sqlKey, Object params, Page<O> page) {
        if (page == null) {
            page = new Page<>();
            List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params,
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        if (page.getTotal() == -1) {
            page.setTotal(countSimple(sqlKey, params));
        }
        return page;
    }


    /**
     * @param param
     * @return Object
     * @throws
     * @Title: getMyBatisSql
     * @Description:
     * @author hankaibo
     * @date 2013-12-19 下午2:07:44
     */
    private MyBatisSql getMyBatisSql(String id, Object param) {
        MyBatisSql myBatisSql = new MyBatisSql();
        MappedStatement ms = sqlSession.getConfiguration().getMappedStatement(id);
        BoundSql boundSql = ms.getBoundSql(param);

        List<ResultMap> resultMaps = ms.getResultMaps();
        if (resultMaps != null && resultMaps.size() > 0) {
            ResultMap resultMap = ms.getResultMaps().get(0);
            myBatisSql.setResultClass(resultMap.getType());
        }
        myBatisSql.setSql(boundSql.getSql());

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Object[] parameterArray = new Object[parameterMappings.size()];
            MetaObject metaObject = param == null ? null : MetaObject.forObject(param, new DefaultObjectFactory(),
                    new DefaultObjectWrapperFactory());
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (param == null) {
                        value = null;
                    } else if (ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(param.getClass())) {
                        value = param;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
                            && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            // TODO
                            value = MetaObject.forObject(value, null, null).getValue(
                                    propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    parameterArray[i] = value;
                }
            }
            myBatisSql.setParameters(parameterArray);
        }
        return myBatisSql;
    }

    /**
     * 分页查询时，查询总数。
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @return 总数
     */
    private int countSimple(String sqlKey, Object params) {
        String fromHql = getMyBatisSql(createSqlKeyName("count" + sqlKey), params).toString();
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";
        return ((Number) sqlSession.selectOne("cn.mypandora.dao.base.countSql", countSql)).intValue();
    }

    /**
     * 分页查询时，查询总数。以order by对SQL语句进行分割，故原SQL语句中多个order by时将错误。
     *
     * @param sqlKey 查询sql的名称
     * @param params 参数
     * @return 总数
     */
    private int count(String sqlKey, Object params) {
        String fromHql = getMyBatisSql(createSqlKeyName(sqlKey), params).toString();
        fromHql = StringUtils.substringBeforeLast(fromHql, "order by");
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";

        return ((Number) sqlSession.selectOne("cn.mypandora.dao.base.countSql", countSql)).intValue();

    }

    /**
     * 分页查询时，查询总数。
     *
     * @param sql 查询sql
     * @return 总数
     */
    private int countBySql(String sql) {
        String fromHql = getMyBatisSql(FIND_BY_SQL, sql).toString();
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";

        return ((Number) sqlSession.selectOne("cn.mypandora.dao.base.countSql", countSql)).intValue();
    }

}
