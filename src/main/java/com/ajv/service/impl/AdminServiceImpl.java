package com.ajv.service.impl;

import com.ajv.entity.Admin;
import com.ajv.interceptor.AdminInterceptor;
import com.ajv.interceptor.impl.AdminInterceptorImpl;
import com.ajv.service.AdminService;
import com.ajv.utils.PageBean;

/**
 * @author ajv
 * @Title:TeacherServiceImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:58
 */
public class AdminServiceImpl implements AdminService {

	private AdminInterceptor adminInterceptor = new AdminInterceptorImpl();

	@Override
	public PageBean findByPage(PageBean pageBean, Admin admin) {
		return adminInterceptor.findByPage(pageBean,admin);
	}

	@Override
	public Admin findByAccount(String account) {
		return adminInterceptor.findByAccount(account);
	}

	@Override
	public boolean addAdmin(Admin admin) {
		return adminInterceptor.addAdmin(admin);
	}

	@Override
	public boolean delAdmin(Integer id) {
		return adminInterceptor.delAdmin(id);
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		return adminInterceptor.updateAdmin(admin);
	}
}
