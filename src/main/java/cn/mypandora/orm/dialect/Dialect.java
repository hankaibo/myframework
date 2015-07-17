/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.dialect;

/**
 * 方言接口。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface Dialect {
    /**
     * @param sql    原始查询SQL
     * @param offset 开始记录索引（从零开始）
     * @param limit  每页记录大小
     * @return 返回数据库相关的分页SQL语句
     */
    public abstract String getPageSql(String sql, int offset, int limit);

    public static enum Type {
        MYSQL {
            public String getValue() {
                return "mysql";
            }
        },
        MSSQL {
            public String getValue() {
                return "sqlserver";
            }
        },
        ORACLE {
            public String getValue() {
                return "oracle";
            }
        }
    }

}
