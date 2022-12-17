package com.ajv.service;

import com.ajv.entity.Clazz;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:ClassService
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:45
 */
public interface ClassService {

	List<Clazz> findALl();

	PageBean findByPage(PageBean pageBean, Clazz clazz);

	Clazz findById(Integer id);

	int getClassCount(String className);

	boolean addClass(Clazz clazz);

	boolean delClass(Integer id);

	boolean updateClass(Clazz clazz);
}
