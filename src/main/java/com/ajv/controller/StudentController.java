package com.ajv.controller;

import com.ajv.entity.Clazz;
import com.ajv.entity.Student;
import com.ajv.service.ClassService;
import com.ajv.service.StudentService;
import com.ajv.service.impl.ClassServiceImpl;
import com.ajv.service.impl.StudentServiceImpl;
import com.ajv.utils.PageBean;
import com.ajv.utils.ResultEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentController extends HttpServlet {

	private StudentService studentService = new StudentServiceImpl();
	private ClassService classService = new ClassServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String method = req.getParameter("method");
		if (method == null) {
			String currPage = req.getParameter("page");
			String limitSize = req.getParameter("limit");
			String keyword = req.getParameter("keyword");

			String clazzName = req.getParameter("clazz_name");
			if (clazzName == null){
				clazzName = "";
			}
			String sName = req.getParameter("sName");
			Student student = new Student();
			student.setClazz_name(clazzName);
			student.setName(sName);

			PageBean pageBean = new PageBean(currPage, limitSize);

			PageBean studentList = studentService.findAllWithPage(pageBean, student);
			List<Clazz> classList = classService.findALl();

			req.setAttribute("classList",classList);
			req.setAttribute("studentList", new ResultEntity<>("200", null, studentList));

			if ("teacherInfo".equals(keyword)){
				req.getRequestDispatcher("static/jsp/teacher/manager-student.jsp").forward(req,resp);
			} else if ("adminInfo".equals(keyword)) {
				req.getRequestDispatcher("static/jsp/admin/manager-student.jsp").forward(req,resp);
			}else {
				req.getRequestDispatcher("static/jsp/student/main-student.jsp").forward(req, resp);
			}
		} else if ("toEdit".equals(method)) {
			String account = req.getParameter("account");
			String keyword = req.getParameter("keyword");
			Student s = studentService.findByAccount(account);
			List<Clazz> clazzList = classService.findALl();
			session.setAttribute("info", s);
			req.setAttribute("classList",clazzList);
			req.setAttribute("status","isStudent");

			if ("teacherPage".equals(keyword)){
				req.getRequestDispatcher("static/jsp/teacher/teacher-edit.jsp").forward(req, resp);
			} else if ("adminInfo".equals(keyword)) {
				req.getRequestDispatcher("static/jsp/admin/page-edit.jsp").forward(req,resp);
			}else {
				req.getRequestDispatcher("static/jsp/student/student-profile.jsp").forward(req, resp);
			}
		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String telephone = req.getParameter("telephone");
		String address = req.getParameter("address");
		String introducation = req.getParameter("introducation");
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setGender(gender);
		student.setPassword(password);
		student.setEmail(email);
		student.setTelephone(telephone);
		student.setAddress(address);
		student.setIntroducation(introducation);

		studentService.updateStudent(student);

		resp.sendRedirect("student");
	}
}
