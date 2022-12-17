package com.ajv.service.impl;

import com.ajv.entity.Clazz;
import com.ajv.interceptor.ClassInterceptor;
import com.ajv.interceptor.impl.ClassInterceptorImpl;
import com.ajv.service.ClassService;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:ClassServiceImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:46
 */
public class ClassServiceImpl implements ClassService {

	private ClassInterceptor classInterceptor = new ClassInterceptorImpl();

	@Override
	public List<Clazz> findALl() {
		return classInterceptor.findAll();
	}

	@Override
	public PageBean findByPage(PageBean pageBean, Clazz clazz) {
		return classInterceptor.findByPage(pageBean,clazz);
	}

	@Override
	public Clazz findById(Integer id){
		return classInterceptor.findById(id);
	}

	@Override
	public int getClassCount(String className) {
		return classInterceptor.getClassCount(className);
	}

	@Override
	public boolean addClass(Clazz clazz) {
		return classInterceptor.addClass(clazz);
	}

	@Override
	public boolean delClass(Integer id) {
		return classInterceptor.delClass(id);
	}

	@Override
	public boolean updateClass(Clazz clazz) {
		return classInterceptor.updateClass(clazz);
	}
}
