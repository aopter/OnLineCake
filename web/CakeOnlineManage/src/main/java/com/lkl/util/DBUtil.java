package com.lkl.util;

import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @author 刘凯丽
 * @createTime 2020/10/28 16:39
 * @projectName first
 * @className DBUtil.java
 * @description 数据库封装类
 */
@Component
public class DBUtil {

    private static String driver;
    private static String connStr;
    private static String user;
    private static String pwd;

    private static DBUtil dbUtil;

    //	连接对象
    private static Connection conn;

    // 静态代码块调用
    static {
        try {
//			加载配置文件
//            loadProperties();
            driver = "com.mysql.jdbc.Driver";
            connStr = "jdbc:mysql://localhost:3306/cakeonline_db?useUnicode=true&characterEncoding=UTF-8";
            user = "root";
            pwd = "";

//			得到连接对象
            getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void getConnection() throws ClassNotFoundException, SQLException {
        if (null == conn) {
//			加载驱动
            Class.forName(driver);
//			获取连接对象
            conn = DriverManager.getConnection(connStr, user, pwd);
        }
    }

//	数据操作

    /**
     * 查询数据
     *
     * @param sql 查询数据的sql语句
     * @return 查询到的数据
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ResultSet queryDate(String sql) throws ClassNotFoundException, SQLException {
        // 连接到数据库
        Statement stmt = conn.createStatement();
        // 执行查询
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    /**
     * 判断数据是否存在
     *
     * @param sql 查询的sql语句
     * @return 存在则返回true，否则返回false
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean isExist(String sql) throws ClassNotFoundException, SQLException {
        // 连接到数据库
        Statement stmt = conn.createStatement();
        // 执行查询
        ResultSet rs = stmt.executeQuery(sql);
        return rs.next();
    }

    /**
     * 插入数据
     *
     * @param sql 执行插入的sql语句
     * @return 插入记录的行
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int addDataToTable(String sql) throws ClassNotFoundException, SQLException {
        // 连接到数据库
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    /**
     * 修改或删除数据
     *
     * @param sql 待操作的SQL语句
     * @return 修改或删除的记录行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int updateData(String sql) throws ClassNotFoundException, SQLException {
        // 连接到数据库
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    //	关闭数据库连接
    public void closeDB() throws SQLException {
//		如果连接对象不为空  并且没有关闭 才需要关闭
        if (null != conn && conn.isClosed()) {
            conn.close();
        }
    }
}
