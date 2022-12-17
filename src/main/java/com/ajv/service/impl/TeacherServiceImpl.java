package com.ajv.service.impl;

import com.ajv.entity.Teacher;
import com.ajv.interceptor.TeacherInterceptor;
import com.ajv.interceptor.impl.TeacherInterceptorImpl;
import com.ajv.service.TeacherService;
import com.ajv.utils.PageBean;

/**
 * @author ajv
 * @Title:TeacherServiceImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:58
 */
public class TeacherServiceImpl implements TeacherService {

	private TeacherInterceptor teacherInterceptor = new TeacherInterceptorImpl();

	@Override
	public PageBean findByPage(PageBean pageBean, Teacher teacher) {
		return teacherInterceptor.findByPage(pageBean,teacher);
	}

	@Override
	public Teacher findByAccount(String account) {
		return teacherInterceptor.findByAccount(account);
	}

	@Override
	public boolean addTeacher(Teacher teacher) {
		return teacherInterceptor.addTeacher(teacher);
	}

	@Override
	public boolean delTeacher(Integer id) {
		return teacherInterceptor.delTeacher(id);
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		return teacherInterceptor.updateTeacher(teacher);
	}
}
