package cn.tedu.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.jdbc.JDBCUtils;

public class MyPoolTest {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MyPool pool = new MyPool();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// conn =
			// DriverManager.getConnection("jdbc:mysql:///db_batch","root",
			// "root");
			conn = pool.getConnection();
			ps = conn.prepareStatement("select * from account");
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} finally {
					rs = null;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} finally {
					ps = null;
				}
			}
//			pool.returnConnection(conn);
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} finally {
					conn = null;
				}
			}
		}
	}
}
