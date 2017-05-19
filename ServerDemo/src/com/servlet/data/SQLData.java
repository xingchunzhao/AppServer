package com.servlet.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SQLData
 */
@WebServlet("/SQLData")
public class SQLData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/csvt";
	                              
	//数据库的用户名与密码
	static final String USER = "root";
	static final String PASS = "password";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SQLData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection conn = null;
		Statement stmt = null;
		
		//设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "Servlet Mysql 测试-菜鸟教程";
		String docType = "<!DOCTYPE html>\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n");
		try{
			// 注册 JDBC 驱动器
			Class.forName(JDBC_DRIVER);
			
			//执行 SQL 查询
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//展开结果集数据库
			stmt = conn.createStatement();
			String sql;
			sql = "select username,password from login";
			ResultSet rs = stmt.executeQuery(sql);
			
			//展开结果集数据库
			while(rs.next())
			{
				//通过字段检索
				//int id  = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				//输出数据
				//out.println("ID" + id);
				out.println("username:" + username);
				out.println(",password:" + password);
				out.println("<br />");
				
			}
			
			out.println("</body></html>");
			
			//完成后关闭
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(SQLException Se)
		{
			//处理 JDBC 错误
			Se.printStackTrace();
		}catch(Exception e)
		{
			//处理 Class.forName 错误
			e.printStackTrace();
		}finally{
			
			//最后是用于关闭资源的块
			try{
				if (stmt != null)
				{
					stmt.close();
				}
			}catch(SQLException Se2)
			{
				Se2.printStackTrace();
			}
			try{
				if (conn != null)
				{
					conn.close();
				}
			}catch(SQLException Se3)
			{
				Se3.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
