package com.ajv.service;

import com.ajv.entity.Admin;
import com.ajv.utils.PageBean;

/**
 * @author ajv
 * @Title:Teacher
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:56
 */
public interface AdminService {

	PageBean findByPage(PageBean pageBean, Admin admin);

	Admin findByAccount(String account);

	boolean addAdmin(Admin admin);

	boolean delAdmin(Integer id);

	boolean updateAdmin(Admin admin);
}
