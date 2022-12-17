package com.ajv.interceptor;

import com.ajv.entity.Clazz;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:ClassInterceptor
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:38
 */
public interface ClassInterceptor {

	List<Clazz> findAll();

	PageBean findByPage(PageBean pageBean, Clazz clazz);

	Clazz findById(Integer id);

	void setClassCount(String count,String className);

	int getClassCount(String className);

	boolean addClass(Clazz clazz);

	boolean delClass(Integer id);

	boolean updateClass(Clazz clazz);
}
