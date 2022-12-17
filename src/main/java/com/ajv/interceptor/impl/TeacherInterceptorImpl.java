package com.ajv.interceptor.impl;

import com.ajv.entity.Teacher;
import com.ajv.interceptor.TeacherInterceptor;
import com.ajv.utils.BaseDao;
import com.ajv.utils.PageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ajv
 * @Title:TeacherInterceptorImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:44
 */
public class TeacherInterceptorImpl implements TeacherInterceptor {
	private final BaseDao baseDao = new BaseDao();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet result;

	@Override
	public List<Teacher> findAll(){
		String sql = "select * from tb_teacher";

		List<Teacher> teacherList = new ArrayList<>();

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String tno = result.getString("tno");
				String name = result.getString("name");
				String gender = result.getString("gender");
				String password = result.getString("password");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String address = result.getString("address");
				String className = result.getString("clazz_name");
				Teacher teacher = new Teacher(id,tno,name,gender,password,email,telephone,address,className);
				teacherList.add(teacher);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return teacherList;
	}

	@Override
	public PageBean findByPage(PageBean pageBean, Teacher teacher){
		String sql;
		if (teacher.getTno() != null && teacher.getName() != null){
			sql = "select * from tb_teacher where tno like ? and `name` like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+teacher.getTno()+"%");
				pstmt.setString(2,"%"+teacher.getName()+"%");
				pstmt.setInt(3, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(4, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else if (teacher.getTno() != null && teacher.getName() == null) {
			sql = "select * from tb_teacher where tno like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+teacher.getTno()+"%");
				pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(3, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else if (teacher.getTno() == null && teacher.getName() != null) {
			sql = "select * from tb_teacher where `name` like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+teacher.getName()+"%");
				pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(3, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}else {
			sql = "select * from tb_teacher limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(2, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		List<Teacher> teacherList = new ArrayList<>();

		try {
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String sno = result.getString("tno");
				String name = result.getString("name");
				String gender = result.getString("gender");
				String password = result.getString("password");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String address = result.getString("address");
				String className = result.getString("clazz_name");
				Teacher t = new Teacher(id,sno,name,gender,password,email,telephone,address,className);
				teacherList.add(t);
			}
			pageBean.setCountSize(this.findAll().size());
			pageBean.setData(teacherList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return pageBean;
	}

	public Teacher findByAccount(String account){
		String sql = "select * from tb_teacher where `name` = ?";
		Teacher teacher = new Teacher();
		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,account);
			result = pstmt.executeQuery();
			while (result.next()){
				teacher.setId(result.getInt("id"));
				teacher.setTno(result.getString("tno"));
				teacher.setName(result.getString("name"));
				teacher.setGender(result.getString("gender"));
				teacher.setPassword(result.getString("password"));
				teacher.setEmail(result.getString("email"));
				teacher.setTelephone(result.getString("telephone"));
				teacher.setAddress(result.getString("address"));
				teacher.setClazz_name(result.getString("clazz_name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}
		return teacher;
	}

	@Override
	public boolean addTeacher(Teacher teacher) {
		boolean flag;
		String sql = "insert into tb_teacher values(null,?,?,?,'123456',?,?,?,?)";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,teacher.getTno());
			pstmt.setString(2,teacher.getName());
			pstmt.setString(3,teacher.getGender());
			pstmt.setString(4,teacher.getEmail());
			pstmt.setString(5,teacher.getTelephone());
			pstmt.setString(6,teacher.getAddress());
			pstmt.setString(7,teacher.getClazz_name());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}

	@Override
	public boolean delTeacher(Integer id) {
		boolean flag;
		String sql = "delete from tb_teacher where id = ?";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,id);
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		boolean flag;
		String sql;
		if (teacher.getTno() == null && teacher.getClazz_name() == null){
			sql = "update tb_teacher set `name` = ?,gender = ?, password = ?, email = ?, telephone = ?, address = ? where id = ?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,teacher.getName());
				pstmt.setString(2,teacher.getGender());
				pstmt.setString(3,teacher.getPassword());
				pstmt.setString(4,teacher.getEmail());
				pstmt.setString(5,teacher.getTelephone());
				pstmt.setString(6,teacher.getAddress());
				pstmt.setInt(7,teacher.getId());
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}else{
			sql = "update tb_teacher set tno = ?, `name` = ?,gender = ?, password = ?, email = ?, telephone = ?, address = ?,clazz_name = ? where id = ?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,teacher.getTno());
				pstmt.setString(2,teacher.getName());
				pstmt.setString(3,teacher.getGender());
				pstmt.setString(4,teacher.getPassword());
				pstmt.setString(5,teacher.getEmail());
				pstmt.setString(6,teacher.getTelephone());
				pstmt.setString(7,teacher.getAddress());
				pstmt.setString(8,teacher.getClazz_name());
				pstmt.setInt(9,teacher.getId());
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}

		try {
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}
}
