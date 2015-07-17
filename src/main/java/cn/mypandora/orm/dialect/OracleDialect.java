/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dialect;

/**
 * Oracle分页支持。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class OracleDialect implements Dialect {
    /**
     * @param sql    原始查询SQL
     * @param offset 开始记录索引（从零开始）
     * @param limit  每页记录大小
     * @return 返回数据库相关的分页SQL语句
     */
    @Override
    public String getPageSql(String sql, int offset, int limit) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" for update")) {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }

        StringBuffer pageSql = new StringBuffer(sql.length() + 100);
        pageSql.append("select * from ( select row_.*, rownum rownum_ from ( ");
        pageSql.append(sql);
        pageSql.append(" ) row_ ) where rownum_ > " + offset + " and rownum_ <= " + (offset + limit));
        if (isForUpdate) {
            pageSql.append(" for update");
        }
        return pageSql.toString();
    }

}
