package com.ajv.controller;

import com.ajv.entity.Clazz;
import com.ajv.entity.Grade;
import com.ajv.service.ClassService;
import com.ajv.service.GradeService;
import com.ajv.service.impl.ClassServiceImpl;
import com.ajv.service.impl.GradeServiceImpl;
import com.ajv.utils.PageBean;
import com.ajv.utils.ResultEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ajv
 * @Title:ClassController
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:47
 */
@WebServlet("/clazz")
public class ClassController extends HttpServlet {

	private ClassService classService = new ClassServiceImpl();

	private GradeService gradeService = new GradeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset-utf-8");

		String currPage = req.getParameter("page");
		String limitSize = req.getParameter("limit");
		String gradeName = req.getParameter("gradeName");
		String className = req.getParameter("cName");
		String status = req.getParameter("status");
		String method = req.getParameter("method");

		if (method == null){
			PageBean pageBean = new PageBean(currPage,limitSize);
			Clazz clazz = new Clazz();
			clazz.setGrade_name(gradeName);
			clazz.setName(className);

			PageBean classList = classService.findByPage(pageBean,clazz);
			List<Grade> gradeList = gradeService.findAll();
			req.setAttribute("gradeInfo",gradeList);
			req.setAttribute("classList",new ResultEntity<>("200",null,classList));

			if (status == null){
				req.getRequestDispatcher("static/jsp/admin/manager-class.jsp").forward(req,resp);
			}else if ("isTeacherWithStudent".equals(status)){
				req.getRequestDispatcher("static/jsp/teacher/add-student.jsp").forward(req,resp);
			}else if ("isStudent".equals(status) || "isTeacher".equals(status) || "isAdmin".equals(status)) {
				req.setAttribute("status",status);
				req.getRequestDispatcher("static/jsp/admin/page-add.jsp").forward(req, resp);
			}
		}else if ("toEdit".equals(method)){
			Integer id = Integer.parseInt(req.getParameter("id"));
			Clazz clazz = classService.findById(id);
			List<Grade> gradeList = gradeService.findAll();
			req.setAttribute("gradeList",gradeList);
			req.setAttribute("info",clazz);
			req.setAttribute("status","isClass");
			req.getRequestDispatcher("static/jsp/admin/page-edit.jsp").forward(req,resp);
		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
