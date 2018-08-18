package cn.tedu.jdbc.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Test {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ComboPooledDataSource  cpds = new ComboPooledDataSource();
//		ComboPooledDataSource  cpds = new ComboPooledDataSource("config2");
		
		try {
//			cpds.setDriverClass("com.mysql.jdbc.Driver");
//			cpds.setJdbcUrl("jdbc:mysql:///mydb1");
//			cpds.setUser("root");
//			cpds.setPassword("root");
			
			Class.forName("com.mysql.jdbc.Driver");
			// conn =
			// DriverManager.getConnection("jdbc:mysql:///db_batch","root",
			// "root");
			conn = cpds.getConnection();
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
