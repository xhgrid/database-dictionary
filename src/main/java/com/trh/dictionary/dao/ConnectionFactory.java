package com.trh.dictionary.dao;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
/**
 * 数据库连接工厂类
 *
 * @author
 * @create 2019-08-28 16:28
 */
public class ConnectionFactory {
    public static String mySql="mySql";
    public static String pgSql="pgSql";

    /**
     * 得到数据库连接
     * @param url 地址
     * @param userName 用户名
     * @param passWord 密码
     * @param driverName 驱动名：mySql、pgSql
     * @return
     */
    public static Connection getConnection(String url,String userName,String passWord,String driverName){
        Connection connection = null;
        //创建驱动
        try {
            if (mySql.equals(driverName)){
                Class.forName("org.gjt.mm.mysql.Driver");
            }else if (pgSql.equals(driverName)){
                Class.forName("org.postgresql.Driver");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //创建连接
        try {
            connection = DriverManager.getConnection(url, userName, passWord);
            if (connection.isClosed()) {
                System.out.println("------------------- the connect is closed --------------");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 释放资源
     * @param connection 连接
     * @param preparedStatement
     * @param resultSet 结果集
     */
    public static void releaseResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet,Statement statement){
        if(null != resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != statement){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != preparedStatement){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
