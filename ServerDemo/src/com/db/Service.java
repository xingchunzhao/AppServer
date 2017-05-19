package com.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 通过调用本类的方法完成登录、注册服务
 */

//import com.db.DBManager;
public class Service {
	
	public Boolean login(String username, String password)
	{
		//获取sql查询语句
		String logSql = "select * from login where username='"+username
				+ "' and password='" + password + "'";
		//System.out.println(logSql);
		//获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		
		//操作DB对象
		try{
			ResultSet rs = sql.executeQuery(logSql);
			if(rs.next())
			{
				return true;
			}
			//System.out.println(rs);
		}catch(Exception se)
		{
			se.printStackTrace();
		}
		
		sql.closeDB();
		return false;
		
	}
	
	public Boolean register(String username, String password)
	{
		//获取sql插入语句
		String regSql = "insert into login values('"+ username+ "," + password +"')";
		//获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		int ret = sql.executeUpdate(regSql);
		if (ret != 0)
		{
			sql.closeDB();
			return true;
		}
		sql.closeDB();
		return false;
	}
	
}
