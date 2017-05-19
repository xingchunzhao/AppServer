package com.db;
/*
 * 处理Http请求（get/post）
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogLet
 */
@WebServlet("/LogLet")
public class LogLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogLet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//接受客户信息
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO-8859-1"),"utf-8");
		String password = request.getParameter("password");
		System.out.println(username + "--" + password);
		
		//新建服务对象
		Service serv = new Service();
		
		//验证处理
		Boolean loged = serv.login(username, password);
		System.out.println(loged);
		if(loged == true)
		{
			System.out.println("Succss");
			request.getSession().setAttribute(username, password);
			//返回到客户端
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("用户名：" + username);
			out.println("密码：" + password);
			out.flush();
			out.close();
		}
		else
		{
			System.out.println("Failed");
			//System.out.println(loged);
			//request.getSession();
			//返回到客户端
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("用户名或者密码错误");
			out.flush();
			out.close();
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
