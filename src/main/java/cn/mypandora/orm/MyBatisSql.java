/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获取MyBatis运行时SQL。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class MyBatisSql {
    // 运行期的sql
    private String sql;
    // 运行期的参数
    private Object[] parameters;
    // <select id="XXX" resultType="ZZZ">中的resultType
    private Class<?> resultClass;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?> getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class<?> resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public String toString() {
        if (parameters == null || sql == null) {
            return "";
        }

        List<Object> parametersArray = Arrays.asList(parameters);
        List<Object> list = new ArrayList<Object>(parametersArray);

        for (Object object : list) {

            if (object instanceof Number) {
                sql = sql.replaceFirst("\\?", object.toString());
            } else {
                sql = sql.replaceFirst("\\?", "'" + object + "'");
            }

        }
        return sql.replaceAll("(\r?\n(\\s*\r?\n)+)", "\r\n");
    }

}
