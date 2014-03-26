package cn.littleprincess.orm.dialect;

/**
 * @ClassName:Dialect
 * @Description:方言接口。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:58:24
 * @UpdateRemark:What is modified?
 */
public interface Dialect {

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

    /**
     * @Title: getPageSql
     * @Description: 获取分页SQL
     * @param sql
     *            原始查询SQL
     * @param offset
     *            开始记录索引（从零开始）
     * @param limit
     *            每页记录大小
     * @return 返回数据库相关的分页SQL语句
     * @author hankaibo
     * @date 2013-12-26 上午11:04:00
     * @throws
     */
    public abstract String getPageSql(String sql, int offset, int limit);

}
