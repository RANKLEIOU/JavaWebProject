package com.ajv.interceptor;

import com.ajv.entity.Teacher;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:TeacherInterceptor
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:41
 */
public interface TeacherInterceptor {

	List<Teacher> findAll();

	PageBean findByPage(PageBean pageBean,Teacher teacher);

	Teacher findByAccount(String account);

	boolean addTeacher(Teacher teacher);

	boolean delTeacher(Integer id);

	boolean updateTeacher(Teacher teacher);
}
