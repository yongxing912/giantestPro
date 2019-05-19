package com.yongxing912.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

//	private String dbUrl="jdbc:mysql://192.168.100.200:3306/db_gt_cp?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private String dbUrl="jdbc:mysql://localhost:3306/db_gt_cp?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private String dbUserName="root";
	private String dbPassword="admin";
	private String jdbcName="com.mysql.cj.jdbc.Driver";
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * �ر����ݿ�����
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
