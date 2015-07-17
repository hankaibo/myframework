/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dialect;

/**
 * sql server分页支持。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class SQLServerDialect implements Dialect {
    /**
     * @param sql    原始查询SQL
     * @param offset 开始记录索引（从零开始）
     * @param limit  每页记录大小
     * @return 返回数据库相关的分页SQL语句
     */
    @Override
    public String getPageSql(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuffer pageSql = new StringBuffer(sql.length() + 100);
        // TODO 其实这里还是有一点问题的，就是排序问题，指定死了，有解决的提供一下，等复习到Hibernate看看Hibernat内部是如何实现的。
        pageSql.append("select * from(select a.*,row_number() over (order by id desc) rownum from( ");
        pageSql.append(sql);
        pageSql.append(") a )b where rownum> " + offset + " and rownum <= " + (offset + limit));
        return pageSql.toString();
    }
}
