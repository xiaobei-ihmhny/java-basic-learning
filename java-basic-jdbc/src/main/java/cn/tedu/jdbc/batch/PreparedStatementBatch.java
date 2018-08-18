package cn.tedu.jdbc.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.jdbc.JDBCUtils;

public class PreparedStatementBatch {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///db_batch","root", "root");
			ps = conn.prepareStatement("insert into tb_batch values(null, ?)");
			for(int i=0; i<10000; i++){
				System.out.println(i);
				ps.setString(1,"name"+i);
				ps.addBatch();
			}
			ps.executeBatch();
			System.out.println("执行成功~!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}
}
