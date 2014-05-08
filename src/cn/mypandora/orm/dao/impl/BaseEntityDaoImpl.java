package cn.mypandora.orm.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
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

import cn.mypandora.orm.MyBatisSql;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName:BaseDaoImpl
 * @Description:DAO实现类。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:58:07
 * @UpdateRemark:What is modified?
 * @param <T>
 */
@Repository
public abstract class BaseEntityDaoImpl<T extends BaseEntity> implements IBaseEntityDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseEntityDaoImpl.class);

    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String FIND_BY_ID = "findById";
    private static final String FIND_ALL = "findAll";
    private static final String FIND_BY_SQL = "cn.mypandora.dao.base.dynaSql";
    private static final String SQL_KEY = "SQL Key <";
    private static final String SQL_KEY_END = ">";

    @Autowired
    private SqlSession sqlSession;

    /**
     * @Title: createSqlKeyName
     * @Description: 构造sql配置文件中的key,格式 nameSpace+'.'+sqlKey
     * @param sqlKey
     * @return
     * @return String
     * @author hankaibo
     * @date 2013-12-19 下午1:58:26
     * @throws
     */
    String createSqlKeyName(String sqlKey) {
        String key = getNameSpace() + "." + sqlKey;
        logger.debug(SQL_KEY + key + SQL_KEY_END);
        return key;
    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: getNameSpace 
     * Description:查询sql配置文件命名空间
     * @return
     * @see com.etfhr.framework.orm.BaseDao#getNameSpace()
     */
    // @formatter:on
    public abstract String getNameSpace();

    // @formatter:off
    /*
     * (非 Javadoc) Title: getSql 
     * Description:查询sql
     * @param sqlKey
     * @param param
     * @return
     * @see com.etfhr.framework.orm.BaseDao#getSql(java.lang.String,
     * java.lang.Object)
     */
    // @formatter:on
    public String getSql(String sqlKey, Object param) {
        String fullSqlKey = createSqlKeyName(sqlKey);
        return getMyBatisSql(fullSqlKey, param).toString();
    }

    /**
     * @Title: findBySql
     * @Description: 根据sql语句查询
     * @param sql
     * @return
     * @return List<Map<String,Object>>
     * @author hankaibo
     * @date 2013-12-19 下午2:36:25
     * @throws
     */
    public List<Map<String, Object>> findBySql(String sql) {
        return sqlSession.selectList(FIND_BY_SQL, sql);
    }

    public Page<Map<String, Object>> findMapBySql(String sql, Page<Map<String, Object>> page) {
        if (page == null) {
            page = new Page<Map<String, Object>>();
            List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql);
            page.setResultList(list);
            return page;
        }

        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql,
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        page.setTotal(countBySql(sql));

        return page;

    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: addEntity 
     * Description:添加实体。
     * @param t
     * @see cn.mypandora.orm.IBaseEntityOperation#addEntity(cn.mypandora.orm.BaseEntity )
     */
    // @formatter:on
    @Override
    public void addEntity(T t) {
        sqlSession.insert(createSqlKeyName(ADD), t);
    }

    /**
     * @Title: addEntity
     * @Description: 添加自定义实体。
     * @param sqlKey
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-23 下午1:17:27
     * @throws
     */
    public <O> void addEntity(String sqlKey, O t) {
        sqlSession.insert(createSqlKeyName(sqlKey), t);
    }

    /**
     * @Title: insertByCondetion
     * @Description: 按条件添加实体。
     * @param sqlKey
     * @param params
     * @return void
     * @author hankaibo
     * @date 2013-12-23 下午1:17:50
     * @throws
     */
    public void insertByCondetion(String sqlKey, Object params) {
        sqlSession.insert(createSqlKeyName(sqlKey), params);
    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: updateEntity 
     * Description:更新实体。
     * @param t
     * @see cn.mypandora.orm.IBaseEntityOperation#updateEntity(cn.mypandora.orm. BaseEntity)
     */
    // @formatter:on
    @Override
    public void updateEntity(T t) {
        sqlSession.update(createSqlKeyName(UPDATE), t);
    }

    /**
     * @Title: updateEntity
     * @Description: 更新自定义实体。
     * @param sqlKey
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-23 下午1:18:48
     * @throws
     */
    public <O> void updateEntity(String sqlKey, O t) {
        sqlSession.update(createSqlKeyName(sqlKey), t);
    }

    /**
     * @Title: updateByCondition
     * @Description: 根据条件修改实体。
     * @param sqlKey
     * @param params
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午3:03:21
     * @throws
     */
    public void updateByCondition(String sqlKey, Object params) {
        sqlSession.update(createSqlKeyName(sqlKey), params);
    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: deleteEntity 
     * Description:物理删除一个实体。
     * @param id
     * @see cn.mypandora.orm.IBaseEntityOperation#deleteEntity(java.io.Serializable
     * )
     */
    // @formatter:on
    @Override
    public void deleteEntity(Serializable id) {
        sqlSession.delete(createSqlKeyName(DELETE), id);
    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: bulkDelete 
     * Description:物理批量删除实体。
     * @param ids
     * @see cn.mypandora.orm.IBaseEntityOperation#bulkDelete(java.io.Serializable[])
     */
    // @formatter:on
    @Override
    public void bulkDelete(Serializable[] ids) {
        for (Serializable id : ids) {
            deleteEntity(id);
        }
    }

    /**
     * @Title: deleteByConditions
     * @Description: 根据条件删除实体。
     * @param sqlKey
     * @param params
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午3:01:25
     * @throws
     */
    public void deleteByConditions(String sqlKey, Object params) {
        sqlSession.delete(createSqlKeyName(sqlKey), params);
    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: findById 
     * Description:根据主键查找一个实体。
     * @param id
     * @return
     * @see cn.mypandora.orm.IBaseEntityOperation#findById(java.io.Serializable)
     */
    // @formatter:on
    @Override
    public T findById(Serializable id) {
        return sqlSession.selectOne(createSqlKeyName(FIND_BY_ID), id);
    }

    /**
     * @Title: findEntityByCondition
     * @Description: 根据条件获取一个实体。
     * @param sqlKey
     * @param params
     * @return
     * @return T
     * @author hankaibo
     * @date 2013-12-19 下午3:07:22
     * @throws
     */
    public T findEntityByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * @Title: findObjectByCondition
     * @Description: 根据条件返回单个实体（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     * @param sqlKey
     * @param params
     * @return
     * @return O
     * @author hankaibo
     * @date 2013-12-19 下午6:05:32
     * @throws
     */
    public <O> O findObjectByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    // @formatter:off
    /*
     * (非 Javadoc) Title: findAll 
     * Description:查询并返回所有实体。
     * @return
     * @see cn.mypandora.orm.IBaseEntityOperation#findAll()
     */
    // @formatter:on
    @Override
    public List<T> findAll() {
        return sqlSession.selectList(createSqlKeyName(FIND_ALL));
    }

    /**
     * @Title: findByCondition
     * @Description: 根据条件返回实体列表。
     * @param sqlKey
     * @param params
     * @return
     * @return List<T>
     * @author hankaibo
     * @date 2013-12-19 下午3:10:31
     * @throws
     */
    public List<T> findByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * @Title: findObjectListByCondition
     * @Description: 根据条件返回实体列表（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     * @param sqlKey
     * @param params
     * @return
     * @return List<O>
     * @author hankaibo
     * @date 2013-12-19 下午3:12:07
     * @throws
     */
    public <O> List<O> findObjectListByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * @Title: findOneByCondition
     * @Description: 查询一条记录。
     * @param sqlKey
     * @param params
     * @return
     * @return Map<String,Object>
     * @author hankaibo
     * @date 2013-12-19 下午3:32:13
     * @throws
     */
    public Map<String, Object> findOneByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), params);
    }

    /**
     * @Title: findMapByCondition
     * @Description: 返回Map.
     * @param sqlKey
     * @param params
     * @return
     * @return List<Map<String,Object>>
     * @author hankaibo
     * @date 2013-12-19 下午3:18:28
     * @throws
     */
    public List<Map<String, Object>> findMapByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), params);
    }

    /**
     * @Title: findMapByConditionPage
     * @Description: 返回分页Map.
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<Map<String,Object>>
     * @author hankaibo
     * @date 2013-12-19 下午6:07:30
     * @throws
     */
    public Page<Map<String, Object>> findMapByConditionPage(String sqlKey, Object params, Page<Map<String, Object>> page) {
        if (page == null) {
            page = new Page<Map<String, Object>>();
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

    // @formatter:off
    /*
     * (非 Javadoc) Title: findByCondition 
     * Description:返回分页实体。
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @see cn.mypandora.orm.IBaseEntityOperation#findByCondition(java.lang.String, java.lang.Object, cn.mypandora.orm.Page)
     */
    // @formatter:on
    @Override
    public Page<T> findByCondition(String sqlKey, Object params, Page<T> page) {
        if (page == null) {
            page = new Page<T>();
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
     * @Title: findByCondition
     * @Description: 返回自定义的实体。
     * @param sqlKey
     * @param params
     * @param pageSize
     * @param currentPage
     * @return
     * @return List<O>
     * @author hankaibo
     * @date 2013-12-23 下午1:29:07
     * @throws
     */
    public <O> List<O> findByCondition(String sqlKey, Object params, int pageSize, int currentPage) {
        int offset = (currentPage - 1) * pageSize;
        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params, new RowBounds(offset, pageSize));
        return list;

    }

    /**
     * @Title: findObjectPageByCondition
     * @Description: 分页查询（用于关联查询自定义实体）
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<O>
     * @author hankaibo
     * @date 2013-12-19 下午3:44:30
     * @throws
     */
    public <O> Page<O> findObjectPageByCondition(String sqlKey, Object params, Page<O> page) {
        if (page == null) {
            page = new Page<O>();
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
     * @Title: findOPageByCondition
     * @Description: 分页查询（传入实体，count+sqlKey 为 count一次统计key值，对数据新增操作少的查询）
     *               使用两个查询,查询数据使用sqlKey,查询统统sqlKey为 "count"+sqlKey
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<O>
     * @author hankaibo
     * @date 2013-12-23 下午1:30:18
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <O> Page<O> findOPageByCondition(String sqlKey, Object params, Page<O> page) {
        if (page == null) {
            page = new Page<O>();
            List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), params);
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        page.setResultList((List<O>) sqlSession.selectList(createSqlKeyName(sqlKey), params,
                new RowBounds(offset, page.getPageSize())));
        if (page.getTotal() == -1) {
            page.setTotal(countSimple(sqlKey, params));
        }
        return page;
    }

    /**
     * @Title: countBySql
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param sql
     * @return
     * @return long
     * @author hankaibo
     * @date 2013-12-19 下午2:47:39
     * @throws
     */
    private int countBySql(String sql) {
        String fromHql = getMyBatisSql(FIND_BY_SQL, sql).toString();
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";

        return ((Number) sqlSession.selectOne("cn.mypandora.dao.base.countSql", countSql)).intValue();
    }

    /**
     * @Title: getMyBatisSql
     * @Description:
     * @param fullSqlKey
     * @param param
     * @return
     * @return Object
     * @author hankaibo
     * @date 2013-12-19 下午2:07:44
     * @throws
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
     * @Title: countSimple
     * @Description: 分页查询时，查询总数。
     * @param sqlKey
     * @param params
     * @return
     * @return long
     * @author hankaibo
     * @date 2013-12-19 下午4:09:52
     * @throws
     */
    private int countSimple(String sqlKey, Object params) {
        String fromHql = "";
        fromHql = getMyBatisSql(createSqlKeyName("count" + sqlKey), params).toString();
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";
        return ((Number) sqlSession.selectOne("cn.mypandora.dao.base.countSql", countSql)).intValue();
    }

    /**
     * @Title: count
     * @Description: 分页查询时，查询总数。
     * @param sqlKey
     * @param params
     * @return
     * @return int
     * @author hankaibo
     * @date 2013-12-19 下午3:37:53
     * @throws
     */
    private int count(String sqlKey, Object params) {
        String fromHql = getMyBatisSql(createSqlKeyName(sqlKey), params).toString();
        fromHql = StringUtils.substringBefore(fromHql, "order by");
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";

        return ((Number) sqlSession.selectOne("cn.mypandora.dao.base.countSql", countSql)).intValue();

    }

}
