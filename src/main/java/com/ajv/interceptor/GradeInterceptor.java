package com.ajv.interceptor;

import com.ajv.entity.Grade;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:TeacherInterceptor
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:41
 */
public interface GradeInterceptor {

	List<Grade> findAll();

	PageBean findByPage(PageBean pageBean, Grade grade);

	Grade findById(Integer id);

	boolean addGrade(Grade grade);

	boolean delGrade(Integer id);

	boolean updateGrade(Grade grade);
}
