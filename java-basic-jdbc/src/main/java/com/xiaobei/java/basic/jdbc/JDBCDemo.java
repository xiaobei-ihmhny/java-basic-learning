package com.xiaobei.java.basic.jdbc;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

/**
 * @Auther: Legend
 * @Date: 2018/8/18 09:15
 * @Description:
 */
public class JDBCDemo {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1. 注册数据库驱动
            DriverManager.registerDriver(new Driver());
            //2. 获取数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root",
                    "root");
            //3. 获取传输器对象
            statement = connection.createStatement();
            //4. 传输sql语句到数据库中执行，获取结果集
            resultSet = statement.executeQuery("SELECT * FROM b2b_edition");
            //5. 遍历结果集获取需要的数据
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("当前的name为：" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 关闭资源
            if(null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    resultSet = null;
                }
            }
            if(null != statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    statement = null;
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connection = null;
                }
            }
        }
    }

}
