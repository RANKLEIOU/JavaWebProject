package com.ajv.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ajv
 * @Title:LoginFilter
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/132:30
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String loginName = (String) request.getSession().getAttribute("account");
		if (loginName!=null){
			filterChain.doFilter(servletRequest,servletResponse);
		}else{
			request.getSession().setAttribute("msg","请先登录再访问！");
			request.getRequestDispatcher("/error.jsp").forward(request,response);

		}
	}

	@Override
	public void destroy() {
	}
}
