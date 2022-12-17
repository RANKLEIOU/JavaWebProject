package com.ajv.service;

import com.ajv.entity.Teacher;
import com.ajv.utils.PageBean;

/**
 * @author ajv
 * @Title:Teacher
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:56
 */
public interface TeacherService {

	PageBean findByPage(PageBean pageBean, Teacher teacher);

	Teacher findByAccount(String account);

	boolean addTeacher(Teacher teacher);

	boolean delTeacher(Integer id);

	boolean updateTeacher(Teacher teacher);
}
