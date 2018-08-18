package cn.tedu.jdbc.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyPool implements DataSource{
	private static List<Connection> list = new LinkedList<Connection>();
	static{
		try {
			for(int i=0; i<5; i++){
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql:///mydb1?user=root&password=root");
				list.add(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Connection getConnection() {
		if(list.size() == 0){
			for(int i=0; i<3; i++){
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql:///mydb1?user=root&password=root");
					list.add(conn);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		Connection conn = list.remove(0);
		ConnectionDecorate connDecorate = new ConnectionDecorate(conn, this);
		System.out.println("成功获取一个连接, 池中还剩"+list.size()+"个连接");
		return connDecorate;
	}

	public void returnConnection(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				list.add(conn);
				System.out.println("成功还回一个连接, 池中还剩"+list.size()+"个连接");
			}else{
				throw new RuntimeException("对不起, 还回连接失败!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
