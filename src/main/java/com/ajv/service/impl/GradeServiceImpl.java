package com.ajv.service.impl;

import com.ajv.entity.Grade;
import com.ajv.interceptor.GradeInterceptor;
import com.ajv.interceptor.impl.GradeInterceptorImpl;
import com.ajv.service.GradeService;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:gradeServiceImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:46
 */
public class GradeServiceImpl implements GradeService {

	private GradeInterceptor gradeInterceptor = new GradeInterceptorImpl();

	@Override
	public List<Grade> findAll() {
		return gradeInterceptor.findAll();
	}

	@Override
	public PageBean findByPage(PageBean pageBean, Grade grade) {
		return gradeInterceptor.findByPage(pageBean,grade);
	}

	@Override
	public Grade findById(Integer id){
		return gradeInterceptor.findById(id);
	}

	@Override
	public boolean addGrade(Grade grade) {
		return gradeInterceptor.addGrade(grade);
	}

	@Override
	public boolean delGrade(Integer id) {
		return gradeInterceptor.delGrade(id);
	}

	@Override
	public boolean updateGrade(Grade grade) {
		return gradeInterceptor.updateGrade(grade);
	}
}
