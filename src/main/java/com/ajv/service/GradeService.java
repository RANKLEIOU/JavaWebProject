package com.ajv.service;

import com.ajv.entity.Grade;
import com.ajv.utils.PageBean;

import java.util.List;

/**
 * @author ajv
 * @Title:ClassService
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:45
 */
public interface GradeService {

	List<Grade> findAll();

	PageBean findByPage(PageBean pageBean, Grade grade);

	Grade findById(Integer id);

	boolean addGrade(Grade grade);

	boolean delGrade(Integer id);

	boolean updateGrade(Grade grade);
}
