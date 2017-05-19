package com.db;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegLet
 */
@WebServlet("/RegLet")
public class RegLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegLet() {
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
		boolean reged = serv.register(username, password);
		if(reged)
		{
			System.out.println("Succss");
			request.getSession().setAttribute(username, password);
		}
		else
		{
			System.out.println("Failed");
		}
				
		//返回到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("用户名：" + username);
		out.println("密码：" + password);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
