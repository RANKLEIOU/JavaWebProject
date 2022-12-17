package com.ajv.service.impl;

import com.ajv.entity.Login;
import com.ajv.interceptor.LoginInterceptor;
import com.ajv.interceptor.impl.LoginInterceptorImpl;
import com.ajv.service.LoginService;

/**
 * @author ajv
 * @Title:LoginServiceImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/1111:34
 */
public class LoginServiceImpl implements LoginService {

	private LoginInterceptor loginInterceptor = new LoginInterceptorImpl();

	@Override
	public boolean Login(Login login) {
		return loginInterceptor.login(login);
	}
}
