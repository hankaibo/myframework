package cn.mypandora.orm.dialect;

/**
 * @ClassName:MySQLDialect
 * @Description:mysql 分页支持。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:58:48
 * @UpdateRemark:What is modified?
 */
public class MySQLDialect implements Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    public String getPageSql(String sql, boolean hasOffset) {
        return MySQLDialectHepler.getPageSql(sql, -1, -1);
    }

    @Override
    public String getPageSql(String sql, int offset, int limit) {
        return MySQLDialectHepler.getPageSql(sql, offset, limit);
    }

    public boolean supportsLimit() {
        return true;
    }
}
