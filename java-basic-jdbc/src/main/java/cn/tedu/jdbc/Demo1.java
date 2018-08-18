package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 #六个步骤实现JDBC示例
 drop database if exists mydb1;
 create database mydb1;
 use mydb1;
 create table account(
 id int primary key auto_increment,
 name varchar(20),
 money double
 );
 insert into account values(null, 'a', 1000);
 insert into account values(null, 'b', 1000);
 insert into account values(null, 'c', 1000);
 */
/**
 * jdbc快速入门程序
 *
 * @author sz
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			// 1.注册数据库驱动
			// 问题: 1.驱动注册两次 2.程序和具体的数据库驱动绑死了一起
			// DriverManager.registerDriver(new Driver());
			// 这种注册驱动的方式: 驱动只会注册一次, 并且没有和具体的数据库驱动绑死在一起, 只是和驱动的全路径名绑死了,
			// 后期可以将全路径名字符串提取到配置文件中
			Class.forName("com.mysql.jdbc.Driver");

			// 2.获取数据库连接
			// Connection conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1",
			// "root", "root");
			conn = DriverManager
					.getConnection("jdbc:mysql:///mydb1?user=root&password=root");

			// 3.获取传输器
			stat = conn.createStatement();

			// 4.利用传输器发送sql语句到数据库执行, 获取执行结果
			rs = stat.executeQuery("select * from account");
			// 5.遍历结果集
			while (rs.next()) {
				String name = rs.getString(2);
				System.out.println(name);
			}
			// rs.next();
			// rs.absolute(3);
			// rs.previous();
			// String name = rs.getString(2);
			// System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// 6.释放资源
			if(rs != null){
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally{
					rs = null;
				}
			}
			if(stat != null){
				try {
					stat.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally{
					stat = null;
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally{
					conn = null;
				}
			}
		}
	}

}
