package cn.tedu.jdbc.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.tedu.jdbc.JDBCUtils;

/**
 * statement方式实现批处理
 * @author sz
 *
 */
/*
drop database if exists db_batch;
create database db_batch;
use db_batch;
create table tb_batch(id int primary key auto_increment, name varchar(20));
insert into tb_batch values(null,'a');
insert into tb_batch values(null,'b');
insert into tb_batch values(null,'c');
insert into tb_batch values(null,'d');
 */

public class StatementBatch {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///db_batch","root", "root");
			stat = conn.createStatement();
			stat.addBatch("use db_batch");
			stat.addBatch("create table tb_batch(id int primary key auto_increment, name varchar(20));");
			stat.addBatch("insert into tb_batch values(null,'a')");
			stat.addBatch("insert into tb_batch values(null,'b')");
			stat.addBatch("insert into tb_batch values(null,'c')");
			stat.addBatch("insert into tb_batch values(null,'d')");

			stat.executeBatch();

			System.out.println("执行成功~!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(conn, stat, rs);
		}
	}
}
