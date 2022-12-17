package com.ajv.controller;

import com.ajv.entity.*;
import com.ajv.service.*;
import com.ajv.service.impl.*;
import com.ajv.utils.PageBean;
import com.ajv.utils.ResultEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ajv
 * @Title:TeacherController
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2210:00
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {

	private AdminService adminService = new AdminServiceImpl();
	private TeacherService teacherService = new TeacherServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	private ClassService classService = new ClassServiceImpl();
	private GradeService gradeService = new GradeServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String method = req.getParameter("method");
		if (method == null) {
			String currPage = req.getParameter("page");
			String limitSize = req.getParameter("limit");

			String aName = req.getParameter("aName");
			Admin admin = new Admin();
			admin.setName(aName);

			PageBean pageBean = new PageBean(currPage, limitSize);

			PageBean adminList = adminService.findByPage(pageBean, admin);

			req.setAttribute("adminList", new ResultEntity<>("200", null, adminList));

			req.getRequestDispatcher("static/jsp/admin/main-admin.jsp").forward(req, resp);
		} else if ("toEdit".equals(method)) {
			String account = req.getParameter("account");
			String adminEdit = req.getParameter("adminEdit");
			Admin a = adminService.findByAccount(account);
			session.setAttribute("info", a);
			req.setAttribute("status","isAdmin");
			if ("toEditInfo".equals(adminEdit)){
				req.getRequestDispatcher("static/jsp/admin/page-edit.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("static/jsp/admin/page-profile.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");

		String method = req.getParameter("method");
		String status = req.getParameter("status");
		if ("add".equals(method)){
			if ("isStudent".equals(status)){
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
				resp.sendRedirect("student?keyword=adminInfo");
			}else if ("isTeacher".equals(status)){
				String tno = req.getParameter("tno");
				String tName = req.getParameter("name");
				String gender = req.getParameter("gender");
				String email = req.getParameter("email");
				String telephone = req.getParameter("telephone");
				String address = req.getParameter("address");
				String clazz = req.getParameter("clazz_name");
				Teacher teacher = new Teacher();
				teacher.setTno(tno);
				teacher.setName(tName);
				teacher.setGender(gender);
				teacher.setEmail(email);
				teacher.setTelephone(telephone);
				teacher.setAddress(address);
				teacher.setClazz_name(clazz);
				teacherService.addTeacher(teacher);
				resp.sendRedirect("teacher?keyword=adminInfo");
			}else if ("isClass".equals(status)){
				String cName = req.getParameter("name");
				String cNumber = String.valueOf(classService.getClassCount(cName));
				String introducation = req.getParameter("introducation");
				String headmaster = req.getParameter("headmaster");
				String telephone = req.getParameter("telephone");
				String email = req.getParameter("email");
				String gradeName = req.getParameter("gradeName");
				Clazz clazz = new Clazz();
				clazz.setName(cName);
				clazz.setNumber(cNumber);
				clazz.setIntroducation(introducation);
				clazz.setHeadmaster(headmaster);
				clazz.setTelephone(telephone);
				clazz.setEmail(email);
				clazz.setGrade_name(gradeName);
				classService.addClass(clazz);
				resp.sendRedirect("clazz");
			}else if ("isGrade".equals(status)){
				String gName = req.getParameter("name");
				String introducation = req.getParameter("introducation");
				String manager = req.getParameter("manager");
				String email = req.getParameter("email");
				String telephone = req.getParameter("telephone");
				Grade grade = new Grade();
				grade.setName(gName);
				grade.setIntroducation(introducation);
				grade.setManager(manager);
				grade.setEmail(email);
				grade.setTelephone(telephone);
				gradeService.addGrade(grade);
				resp.sendRedirect("grade");
			}else if ("isAdmin".equals(status)){
				String aName = req.getParameter("name");
				String gender = req.getParameter("gender");
				String email = req.getParameter("email");
				String telephone = req.getParameter("telephone");
				String address = req.getParameter("address");
				Admin admin = new Admin();
				admin.setName(aName);
				admin.setGender(gender);
				admin.setEmail(email);
				admin.setTelephone(telephone);
				admin.setAddress(address);
				adminService.addAdmin(admin);
				resp.sendRedirect("admin");
			}


		}else if ("del".equals(method)){
			Integer id = Integer.parseInt(req.getParameter("id"));
			if ("isStudent".equals(status)){
				studentService.delStudent(id);
				resp.getWriter().write("删除成功！");
			}else if ("isTeacher".equals(status)){
				teacherService.delTeacher(id);
				resp.getWriter().write("删除成功！");
			}else if ("isAdmin".equals(status)){
				adminService.delAdmin(id);
				resp.getWriter().write("删除成功！");
			}else if ("isClass".equals(status)){
				classService.delClass(id);
				resp.getWriter().write("删除成功！");
			}else if ("isGrade".equals(status)){
				gradeService.delGrade(id);
				resp.getWriter().write("删除成功！");
			}

		} else if ("update".equals(method)) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String gender = req.getParameter("gender");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String telephone = req.getParameter("telephone");
			String address = req.getParameter("address");
			String introducation = req.getParameter("introducation");
			String clazzName = req.getParameter("clazzName");

			if ("isTeacher".equals(status)){
				String tno = req.getParameter("tno");
				Teacher teacher = new Teacher();
				teacher.setId(id);
				teacher.setTno(tno);
				teacher.setName(name);
				teacher.setGender(gender);
				teacher.setPassword(password);
				teacher.setEmail(email);
				teacher.setTelephone(telephone);
				teacher.setAddress(address);
				teacher.setClazz_name(clazzName);
				teacherService.updateTeacher(teacher);
				resp.sendRedirect("teacher?keyword=adminInfo");
			}else if ("isStudent".equals(status)){
				String sno = req.getParameter("sno");
				Student student = new Student();
				student.setId(id);
				student.setSno(sno);
				student.setName(name);
				student.setGender(gender);
				student.setPassword(password);
				student.setEmail(email);
				student.setTelephone(telephone);
				student.setAddress(address);
				student.setIntroducation(introducation);
				student.setClazz_name(clazzName);
				studentService.updateStudent(student);
				resp.sendRedirect("student?keyword=adminInfo");
			}else if ("isAdmin".equals(status)){
				Admin admin = new Admin();
				admin.setId(id);
				admin.setName(name);
				admin.setGender(gender);
				admin.setPassword(password);
				admin.setEmail(email);
				admin.setTelephone(telephone);
				admin.setAddress(address);
				adminService.updateAdmin(admin);
				resp.sendRedirect("admin");
			}else if ("isClass".equals(status)){
				String headmaster = req.getParameter("headmaster");
				String gradeName = req.getParameter("gradeName");
				Clazz clazz = new Clazz();
				clazz.setId(id);
				clazz.setName(name);
				clazz.setIntroducation(introducation);
				clazz.setHeadmaster(headmaster);
				clazz.setTelephone(telephone);
				clazz.setEmail(email);
				clazz.setGrade_name(gradeName);
				classService.updateClass(clazz);
				resp.sendRedirect("clazz");
			}else if ("isGrade".equals(status)){
				String manager = req.getParameter("manager");
				Grade grade = new Grade();
				grade.setId(id);
				grade.setName(name);
				grade.setIntroducation(introducation);
				grade.setManager(manager);
				grade.setEmail(email);
				grade.setTelephone(telephone);
				gradeService.updateGrade(grade);
				resp.sendRedirect("grade");
			}

		}
	}
}
