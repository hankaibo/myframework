package cn.mypandora.orm.dialect;

/**
 * @ClassName:SQLServerDialect
 * @Description:sql server分页支持
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:51:59
 * @UpdateRemark:What is modified?
 */
public class SQLServerDialect implements Dialect {

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
