/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm;

import cn.mypandora.orm.dialect.Dialect;
import cn.mypandora.orm.dialect.MySQLDialect;
import cn.mypandora.orm.dialect.OracleDialect;
import cn.mypandora.orm.dialect.SQLServerDialect;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * 实现mybatis提供的拦截器接口，编写我们自己的分页实现，原理就是拦截底层JDBC操作相关的Statement对象，
 * 把前端的分页参数如当前记录索引和每页大小通过拦截器注入到sql语句中，
 * 即在sql执行之前通过分页参数重新生成分页sql,而具体的分页sql实现是分离到Dialect接口中去。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class OffsetLimitInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();

        // 元数据
        MetaObject metaObject = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY);

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaObject.hasGetter("h")) {
            Object object = metaObject.getValue("h");
            metaObject = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaObject.hasGetter("target")) {
            Object object = metaObject.getValue("target");
            metaObject = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }

        // 分页参数
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            // 如果没有提供RowBounds，不做操作
        } else {
            // 获取配置文件参数
            Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
            // 读取配置文件中的配置，确认是什么分页实现
            Dialect.Type databaseType = null;
            try {
                databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
            } catch (Exception e) {
                // ignore
            }
            if (databaseType == null) {
                throw new RuntimeException("the value of the dialect property in SqlMapConfig.xml is not defined : "
                        + configuration.getVariables().getProperty("dialect"));
            }

            Dialect dialect = null;
            switch (databaseType) {
                case MYSQL:
                    dialect = new MySQLDialect();
                    break;
                case MSSQL:
                    dialect = new SQLServerDialect();
                    break;
                case ORACLE:
                    dialect = new OracleDialect();
                    break;
                default:
                    dialect = new MySQLDialect();
            }

            String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
            metaObject.setValue("delegate.boundSql.sql",
                    dialect.getPageSql(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
            metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
            // 输出日志
            if (logger.isDebugEnabled()) {
                logger.debug("生成的分页SQL：" + boundSql.getSql());
            }
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        // do nothing
    }
}
