package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo2 {
	public static void main(String[] args) {
		// 1.添加
		// add();

		// 2.修改
		// update();

		// 3.删除
		// delete();

		// 4.查询
		// find();

	}

	public static void find() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from account where name = 'd'");
			rs = stat.executeQuery("select * from account");
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString("name");
				double money = rs.getDouble(3);
				System.out.println(id + ":" + name + ":" + money);
			}
			// System.out.println("删除成功~!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(conn, stat, rs);
		}
	}

	public static void delete() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate("delete from account where name='a'");
			System.out.println("删除成功~!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(conn, stat, rs);
		}
	}

	public static void update() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate("update account set money = 2000 where name='d'");
			System.out.println("修改成功~!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(conn, stat, rs);
		}
	}

	public static void add() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///mydb1", "root",
					"root");
			stat = conn.createStatement();
			int rows = stat
					.executeUpdate("insert into account values(null, 'd', '888')");
			System.out.println("影响了" + rows + "行");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 6.释放资源
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
			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} finally {
					stat = null;
				}
			}
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
