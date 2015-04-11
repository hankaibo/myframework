package cn.mypandora.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @ClassName: MyEntityUtil
 * @Description: 根据数据库表映射为实体类
 * @Author: kaibo
 * @date: 2014-5-16
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-16 上午9:02:39
 * @UpdateRemark: What is modified?
 */
public class MyEntityUtil {
    private final static String SRC = "src\\main\\java";

    // ****************需要修改的参数*****************
    private final static String PROJCET_NAME = "myframework";// 工程名称
    private final static String PACKAGE_PATH = "cn.mypandora.system";// 指定实体生成所在包的路径
    private final static String AUTHOR_NAME = "hankaibo";// 作者名字
    private final static String TABLE_NAME = "t_base_user";// 表名
    private final static String DESCRIPTION = "实体用户。";// 实体类描述
    // ****************需要修改的参数*****************

    private String[] colNames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private int[] colSizes; // 列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    // private boolean f_sql = false; // 是否需要导入包import java.io.Serializable;

    // 数据库连接
    private static final String URL = "jdbc:mysql://localhost:3316/framework?useUnicode=true&characterEncoding=UTF-8";
    private static final String NAME = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /*
     * 构造函数
     */
    public MyEntityUtil() {
        // 统计列
        ResultSetMetaData rsmd = getResultSetMetaData();
        int size;
        try {
            size = rsmd.getColumnCount();
            colNames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colNames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("date")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String content = parse(colNames, colTypes, colSizes);
        createFile(content);
        // 关闭

    }

    /**
     * @Title: getConnection
     * @Description: 获取数据库连接并得到需要的结果集。
     * @return
     * @return Connection
     */
    private ResultSetMetaData getResultSetMetaData() {
        // 查要生成实体类的表
        String sql = "select * from " + TABLE_NAME;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData resultSetMetaData = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, NAME, PASS);

            preparedStatement = connection.prepareStatement(sql);
            resultSetMetaData = preparedStatement.getMetaData();
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动器错误：" + e);
        } catch (SQLException e) {
            System.out.println("连接数据库错误：" + e);
        } finally {
            // try {
            // connection.close();
            // } catch (SQLException e) {
            // System.out.println("关闭数据库失败：" + e);
            // }
        }
        return resultSetMetaData;
    }

    /**
     * 
     * @Title: parse
     * @Description: 生成实体类主体代码
     * @param colNames
     * @param colTypes
     * @param colSizes
     * @return
     * @return String
     */
    private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();

        /*
         * 此为参考例子。
         * @ProjectName: MyFramework
         * @Package: cn.mypandora.log
         * @ClassName: MyLogAspect
         * @Copyright © hankaibo. All rights reserved.
         * @Author: kaibo
         * @CreateDate: 2014-4-27 下午3:02:55
         */
        sb.append("/**\r\n");
        sb.append(" * @ProjectName: " + PROJCET_NAME + " \r\n");
        sb.append(" * @Package: " + PACKAGE_PATH + " \r\n");
        sb.append(" * @ClassName: " + firstToUpper(camelCase(TABLE_NAME.substring(2))) + " \r\n");
        sb.append(" * @Copyright © " + AUTHOR_NAME + ". All rights reserved. \r\n");
        sb.append(" * @Author: " + AUTHOR_NAME + " \r\n");
        sb.append(" * @CreateDate " + MyDateUtils.getCurrentTime() + "  \r\n");
        sb.append(" * \r\n");
        sb.append(" */ \r\n");

        /*
         * 参考实例。 package cn.mypandora.util; import java.io.File; import
         * java.io.Serializable;
         */
        sb.append("package " + PACKAGE_PATH + ";\r\n");
        sb.append("\r\n");
        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.*;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("\r\n");

        /*
         * @ClassName: MyLogAspect
         * @Description: 增强类。
         * @Author: kaibo
         * @date: 2014-4-27
         * @UpdateUser: kaibo
         * @UpdateDate: 2014-4-27 下午3:02:55
         * @UpdateRemark: What is modified?
         */
        // 实体部分
        sb.append("/**\r\n");
        sb.append(" * @ClassName: " + firstToUpper(camelCase(TABLE_NAME.substring(2))) + " \r\n");
        sb.append(" * @Description: " + DESCRIPTION + " \r\n");
        sb.append(" * @Author: " + AUTHOR_NAME + " \r\n");
        sb.append(" * @date: " + MyDateUtils.getCurrentDate() + " \r\n");
        sb.append(" * @UpdateUser " + AUTHOR_NAME + " \r\n");
        sb.append(" * @UpdateDate " + MyDateUtils.getCurrentTime() + " \r\n");
        sb.append(" * @UpdateRemark: What is modified? \r\n");
        sb.append(" */");

        /*
         * 参考例子。 
         * public class BaseEntity implements Serializable {
         * private static final long serialVersionUID = 1;
         */
        sb.append("\r\npublic class " + firstToUpper(camelCase(TABLE_NAME.substring(2))) + " implements Serializable {\r\n");
        sb.append("\r\n\tprivate static final long serialVersionUID = 1L;\r\n");
        sb.append("\r\n");

        processAllAttrs(sb);// 属性
        processAllMethod(sb);// get set方法

        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * @Title: processAllAttrs
     * @Description: 生成所有属性
     * @param sb
     * @return void
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + camelCase(colNames[i]) + ";");
            sb.append("\t//" + colTypes[i] + "(" + colSizes[i] + ")");
            sb.append("\r\n");
            sb.append("\r\n");
        }

    }

    /**
     * @Title: processAllMethod
     * @Description: 生成所有方法
     * @param sb
     * @return void
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tpublic void set" + firstToUpper(camelCase(colNames[i])) + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + camelCase(colNames[i]) + "){\r\n");
            sb.append("\t\tthis." + camelCase(colNames[i]) + "=" + camelCase(colNames[i]) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + firstToUpper(camelCase(colNames[i])) + "(){\r\n");
            sb.append("\t\treturn " + camelCase(colNames[i]) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        }

    }

    /**
     * @Title: camelCase
     * @Description: 驼峰
     * @param str
     *            t_base_user
     * @return
     * @return String
     */
    private String camelCase(String str) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (str == null || str.isEmpty() || !str.contains("_")) {
            // 没必要转换
            return str;
        }
        // 用下划线将原始字符串分割
        String camels[] = str.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * @Title: sqlType2JavaType
     * @Description: 获得列的数据类型
     * @param sqlType
     * @return
     * @return String
     */
    private String sqlType2JavaType(String sqlType) {
        switch (sqlType.toLowerCase()) {
        case "bit":
            return "Boolean";
        case "tinyint":
            return "Byte";
        case "short":
            return "Short";
        case "int":
            return "Integer";
        case "long":
            return "Long";
        case "float":
            return "Float";
        case "decimal":
        case "numeric":
        case "real":
        case "money":
        case "smallmoney":
            return "Double";
        case "varchar":
        case "char":
        case "nvarchar":
        case "nchar":
        case "text":
            return "String";
        case "timestamp":
        case "datetime":
        case "date":
        case "time":
        case "year":
            return "Date";
        case "image":
            return "Blod";
        default:
            return null;
        }
    }

    /**
     * @Title: createFile
     * @Description: 写入文件。
     * @param content
     * @return void
     */
    private void createFile(String content) {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + SRC + File.separator
                    + PACKAGE_PATH.replace(".", "\\") + File.separator;
            String fileName=firstToUpper(camelCase(TABLE_NAME.substring(2))) + ".java";
            File file ;
            file= new File(filePath);
            if(!file.exists()){
                file.mkdir();
            }
            file= new File(filePath+fileName);
            System.out.println("**********文件路径**********");
            System.out.println(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
            System.out.println("**********生成成功！请刷新目录**********");
        } catch (IOException e) {
            System.out.println("系统找不到指定的路径:" + e);
        }
    }

    private String firstToUpper(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
    public static void main(String[] args) {
        new MyEntityUtil();

    }
}
