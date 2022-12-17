package com.ajv.controller;

import com.ajv.entity.Grade;
import com.ajv.service.GradeService;
import com.ajv.service.impl.GradeServiceImpl;
import com.ajv.utils.PageBean;
import com.ajv.utils.ResultEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ajv
 * @Title:GradeController
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/258:24
 */
@WebServlet("/grade")
public class GradeController extends HttpServlet {

	private GradeService gradeService = new GradeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset-utf-8");

		String currPage = req.getParameter("page");
		String limitSize = req.getParameter("limit");
		String gName = req.getParameter("gName");
		String status = req.getParameter("status");
		String method = req.getParameter("method");

		if (method == null){
			PageBean pageBean = new PageBean(currPage,limitSize);
			Grade grade = new Grade();
			grade.setName(gName);

			PageBean gradeList = gradeService.findByPage(pageBean,grade);
			req.setAttribute("gradeList",new ResultEntity<>("200",null,gradeList));

			if (status == null){
				req.getRequestDispatcher("static/jsp/admin/manager-grade.jsp").forward(req,resp);
			}else if ("isClass".equals(status) || "isGrade".equals(status)){
				req.setAttribute("status",status);
				req.getRequestDispatcher("static/jsp/admin/page-add.jsp").forward(req,resp);
			}
		}else if ("toEdit".equals(method)){
			Integer id = Integer.parseInt(req.getParameter("id"));
			Grade grade = gradeService.findById(id);
			req.setAttribute("info",grade);
			req.setAttribute("status","isGrade");
			req.getRequestDispatcher("static/jsp/admin/page-edit.jsp").forward(req,resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
