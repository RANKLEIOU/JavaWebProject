package com.ajv.interceptor;

import com.ajv.entity.Admin;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:TeacherInterceptor
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:41
 */
public interface AdminInterceptor {

	List<Admin> findAll();

	PageBean findByPage(PageBean pageBean, Admin admin);

	Admin findByAccount(String account);

	boolean addAdmin(Admin admin);

	boolean delAdmin(Integer id);

	boolean updateAdmin(Admin admin);
}
