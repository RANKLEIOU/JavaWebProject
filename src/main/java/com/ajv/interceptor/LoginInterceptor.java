package com.ajv.interceptor;

import com.ajv.entity.Login;

/**
 * @author ajv
 * @Title:LoginInterceptor
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/1111:16
 */
public interface LoginInterceptor {
	boolean login(Login login);

}
