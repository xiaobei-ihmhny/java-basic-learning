package cn.tedu.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	public static void main(String[] args) {
		System.out.println("123");
	}

	private static Properties prop = new Properties();

	private JDBCUtils() {
	}

	static {
		try {
			// String path = "bin/config.properties";
			String path = JDBCUtils.class.getClassLoader()
					.getResource("config.properties").getPath();
			File file = new File(path);
			// System.out.println(file.getAbsoluteFile());
			prop.load(new FileInputStream(file));
			// System.out.println(prop.getProperty("driver"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() throws Exception {
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,
				user, password);
		return conn;
	}

	public static void close(Connection conn, Statement stat, ResultSet rs) {
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
