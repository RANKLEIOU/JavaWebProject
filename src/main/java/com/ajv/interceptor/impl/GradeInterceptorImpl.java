package com.ajv.interceptor.impl;

import com.ajv.entity.Grade;
import com.ajv.interceptor.GradeInterceptor;
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
 * @Title:gradeInterceptorImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:44
 */
public class GradeInterceptorImpl implements GradeInterceptor {
	private final BaseDao baseDao = new BaseDao();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet result;

	@Override
	public List<Grade> findAll(){
		String sql = "select * from tb_grade";

		List<Grade> gradeList = new ArrayList<>();

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String manager = result.getString("manager");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String introducation = result.getString("introducation");
				Grade grade = new Grade(id,name,manager,email,telephone,introducation);
				gradeList.add(grade);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return gradeList;
	}

	@Override
	public PageBean findByPage(PageBean pageBean, Grade grade){
		String sql;
		if (grade.getName() != null){
			sql = "select * from tb_grade where `name` like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+grade.getName()+"%");
				pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(3, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			sql = "select * from tb_grade limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(2, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		List<Grade> gradeList = new ArrayList<>();

		try {
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String manager = result.getString("manager");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String introducation = result.getString("introducation");
				Grade t = new Grade(id,name,introducation,manager,email,telephone);
				gradeList.add(t);
			}
			pageBean.setCountSize(this.findAll().size());
			pageBean.setData(gradeList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return pageBean;
	}

	public Grade findById(Integer id){
		String sql = "select * from tb_grade where id = ?";
		Grade grade = new Grade();
		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,id);
			result = pstmt.executeQuery();
			while (result.next()){
				grade.setId(result.getInt("id"));
				grade.setName(result.getString("name"));
				grade.setManager(result.getString("manager"));
				grade.setEmail(result.getString("email"));
				grade.setTelephone(result.getString("telephone"));
				grade.setIntroducation(result.getString("introducation"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}
		return grade;
	}

	@Override
	public boolean addGrade(Grade grade) {
		boolean flag;
		String sql = "insert into tb_grade values(null,?,?,?,?,?)";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,grade.getName());
			pstmt.setString(2,grade.getIntroducation());
			pstmt.setString(3,grade.getManager());
			pstmt.setString(4,grade.getEmail());
			pstmt.setString(5,grade.getTelephone());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}

	@Override
	public boolean delGrade(Integer id) {
		boolean flag;
		String sql = "delete from tb_grade where id = ?";

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
	public boolean updateGrade(Grade grade) {
		boolean flag;
		String sql = "update tb_grade set `name` = ?,manager = ?, email = ?, telephone = ?, introducation = ? where id = ?";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,grade.getName());
			pstmt.setString(2,grade.getManager());
			pstmt.setString(3,grade.getEmail());
			pstmt.setString(4,grade.getTelephone());
			pstmt.setString(5,grade.getIntroducation());
			pstmt.setInt(6,grade.getId());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}
}
