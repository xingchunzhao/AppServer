package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 进行数据库的基本操作
 * 根据传参得到sql语句，
 * 通过DBManager类的 createInstance() 方法实例化对象，
 * 调用本类的操作方法，完成数据操作。
 */
public class DBManager {
	
	//连接数据库常量
	//JDBC 驱动名及数据库 URL
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/csvt";
		                                   
	//数据库的用户名与密码
	public static final String USER = "root";
	public static final String PASS = "password";
	
	//静态成员变量
	private static DBManager per = null;
	private Connection conn = null;
	private Statement stmt = null;
	
	//单态模式-懒汉模式
	private DBManager(){}
	
	public static DBManager createInstance(){
		if (per == null)
		{
			per = new DBManager();
			per.initDB();
		}
		return per;
	}
	
	//加载驱动
	public void initDB()
	{
		try{
			Class.forName(JDBC_DRIVER);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//连接数据库，获取句柄+对象
	public void connectDB()
	{
		System.out.println("Connecting to database...");
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("SqlManager:Connect to database successful.");
	}
	
	//关闭数据库 关闭对象 释放句柄
	public void closeDB()
	{
		System.out.println("close connection to database.");
		try{
			stmt.close();
			conn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Close connection successful");
	}
	
	//查询
	public ResultSet executeQuery(String sql)
	{
		ResultSet rs =null;
		try{
			rs = stmt.executeQuery(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	//增添 、删除、修改
	public int executeUpdate(String sql)
	{
		int ret = 0;
		try{
			ret = stmt.executeUpdate(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

}
