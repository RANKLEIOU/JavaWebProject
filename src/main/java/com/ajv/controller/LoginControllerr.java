package com.ajv.controller;

import com.ajv.entity.Login;
import com.ajv.service.LoginService;
import com.ajv.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ajv
 * @Title:Login
 * @ProjectName JavaWebProject
 * @Description:TODO 登陆验证控制器
 * @data 2022/11/1111:36
 */
@WebServlet("/login")
public class LoginControllerr extends HttpServlet {

	private LoginService loginService = new LoginServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		String username = (String) req.getSession().getAttribute("account");
		if (account.equals(username)){
			req.getSession().removeAttribute(account);
			resp.sendRedirect("index.jsp");
		}else {
			req.getSession().setAttribute("msg","当前用户与登录用户不一致，无法退出登录");
			resp.sendRedirect("error.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String type = req.getParameter("type");

		Login login = new Login();
		login.setAccount(account);
		login.setPassword(password);
		login.setType(type);

		boolean flag = loginService.Login(login);

		HttpSession session = req.getSession();
		if (flag) {
			session.setAttribute("account", account);
			resp.getWriter().write(String.valueOf(true));
		} else {
			session.setAttribute("msg", "登录失败，账号或密码错误！");
			resp.getWriter().write(String.valueOf(false));
		}
	}
}
