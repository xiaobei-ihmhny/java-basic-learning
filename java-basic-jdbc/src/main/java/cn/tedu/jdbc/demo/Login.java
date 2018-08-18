package cn.tedu.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import cn.tedu.jdbc.JDBCUtils;

/*
 #用户登录的功能示例
 create table user(
 id int primary key auto_increment,
 username varchar(50),
 password varchar(50)
 );
 insert into user values(null,'张三','123');
 insert into user values(null,'李四','234');

 */
public class Login {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请登录:");
		System.out.println("请输入用户名:");
		String username = sc.nextLine();
		System.out.println("请输入密码:");
		String password = sc.nextLine();
//		login(username, password);
		loginByPreparedStatement(username, password);
	}


	private static void loginByPreparedStatement(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			// select * from user where username='张三'#' and password=''
			// select * from user where username='张三' or '1=1' and password=''

			if (rs.next()) {
				System.out.println("恭喜,登陆成功~!");
			} else {
				System.out.println("用户名或密码错误~!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}

	}

	private static void login(String username, String password) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			String sql = "select * from user where username='" + username
					+ "' and password='" + password + "'";
			System.out.println(sql);
			// select * from user where username='张三'#' and password=''
			// select * from user where username='张三' or '1=1' and password=''

			rs = stat.executeQuery(sql);
			if (rs.next()) {
				System.out.println("恭喜,登陆成功~!");
			} else {
				System.out.println("用户名或密码错误~!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(conn, stat, rs);
		}

	}
}
