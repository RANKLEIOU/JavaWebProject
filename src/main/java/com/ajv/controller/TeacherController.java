package com.ajv.controller;

import com.ajv.entity.Clazz;
import com.ajv.entity.Student;
import com.ajv.entity.Teacher;
import com.ajv.service.ClassService;
import com.ajv.service.StudentService;
import com.ajv.service.TeacherService;
import com.ajv.service.impl.ClassServiceImpl;
import com.ajv.service.impl.StudentServiceImpl;
import com.ajv.service.impl.TeacherServiceImpl;
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

/**
 * @author ajv
 * @Title:TeacherController
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2210:00
 */
@WebServlet("/teacher")
public class TeacherController extends HttpServlet {

	private TeacherService teacherService = new TeacherServiceImpl();
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

			String tno = req.getParameter("tno");
			String tName = req.getParameter("tName");
			Teacher teacher = new Teacher();
			teacher.setTno(tno);
			teacher.setName(tName);

			PageBean pageBean = new PageBean(currPage, limitSize);

			PageBean teacherList = teacherService.findByPage(pageBean, teacher);

			req.setAttribute("teacherList", new ResultEntity<>("200", null, teacherList));

			if ("adminInfo".equals(keyword)){
				req.getRequestDispatcher("static/jsp/admin/manager-teacher.jsp").forward(req,resp);
			}else{
				req.getRequestDispatcher("static/jsp/teacher/main-teacher.jsp").forward(req, resp);
			}
		} else if ("toEdit".equals(method)) {
			String account = req.getParameter("account");
			String keyword = req.getParameter("keyword");
			List<Clazz> classList = classService.findALl();
			Teacher t = teacherService.findByAccount(account);
			session.setAttribute("info", t);
			req.setAttribute("classList",classList);
			req.setAttribute("status","isTeacher");

			if ("adminInfo".equals(keyword)) {
				req.getRequestDispatcher("static/jsp/admin/page-edit.jsp").forward(req,resp);
			}else {
				req.getRequestDispatcher("static/jsp/teacher/teacher-profile.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");

		String method = req.getParameter("method");
		if ("add".equals(method)){
			String name = req.getParameter("name");
			String sno = req.getParameter("sno");
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			String telephone = req.getParameter("telephone");
			String address = req.getParameter("address");
			String introducation = req.getParameter("introducation");
			String clazz = req.getParameter("clazz_name");
			Student student = new Student();
			student.setName(name);
			student.setSno(sno);
			student.setGender(gender);
			student.setEmail(email);
			student.setTelephone(telephone);
			student.setAddress(address);
			student.setIntroducation(introducation);
			student.setClazz_name(clazz);
			studentService.addStudent(student);
			resp.sendRedirect("student?keyword=teacherInfo");
		}else if ("del".equals(method)){
			Integer id = Integer.parseInt(req.getParameter("id"));
			studentService.delStudent(id);
			resp.getWriter().write("删除成功！");

		} else if ("update".equals(method)) {
			String status = req.getParameter("status");
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String gender = req.getParameter("gender");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String telephone = req.getParameter("telephone");
			String address = req.getParameter("address");
			String introducation = req.getParameter("introducation");

			if ("".equals(status)){
				Teacher teacher = new Teacher();
				teacher.setId(id);
				teacher.setName(name);
				teacher.setGender(gender);
				teacher.setPassword(password);
				teacher.setEmail(email);
				teacher.setTelephone(telephone);
				teacher.setAddress(address);
				teacherService.updateTeacher(teacher);
				resp.sendRedirect("teacher");
			}else{
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
				resp.sendRedirect("student?keyword=teacherInfo");
			}

		}
	}
}
