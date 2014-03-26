package cn.littleprincess.orm.dialect;

/**
 * @ClassName:OracleDialect
 * @Description:Oracle分页支持。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:51:04
 * @UpdateRemark:What is modified?
 */
public class OracleDialect implements Dialect {

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
