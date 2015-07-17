/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dialect;

/**
 * mysql 分页支持。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class MySQLDialect implements Dialect {
    protected static final String SQL_END_DELIMITER = ";";

    /**
     * @param sql    原始查询SQL
     * @param offset 开始记录索引（从零开始）
     * @param limit  每页记录大小
     * @return 返回数据库相关的分页SQL语句
     */
    @Override
    public String getPageSql(String sql, int offset, int limit) {
        return MySQLDialectHepler.getPageSql(sql, offset, limit);
    }

    public String getPageSql(String sql, boolean hasOffset) {
        return MySQLDialectHepler.getPageSql(sql, -1, -1);
    }

    public boolean supportsLimit() {
        return true;
    }
}
