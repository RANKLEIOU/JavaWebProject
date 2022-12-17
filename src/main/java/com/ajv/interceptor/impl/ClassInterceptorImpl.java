package com.ajv.interceptor.impl;

import com.ajv.entity.Clazz;
import com.ajv.interceptor.ClassInterceptor;
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
 * @Title:ClassInterceptorImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/2212:38
 */
public class ClassInterceptorImpl implements ClassInterceptor {

	private final BaseDao baseDao = new BaseDao();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet result;

	@Override
	public List<Clazz> findAll() {
		String sql = "select * from tb_clazz";

		List<Clazz> classList = new ArrayList<>();

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String number = result.getString("number");
				String introducation = result.getString("introducation");
				String headmaster = result.getString("headmaster");
				String telephone = result.getString("telephone");
				String email = result.getString("email");
				String gradeName = result.getString("grade_name");
				Clazz clazz = new Clazz(id,name,number,introducation,headmaster,telephone,email,gradeName);
				classList.add(clazz);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return classList;
	}

	@Override
	public PageBean findByPage(PageBean pageBean, Clazz clazz) {
		String sql;
		if (clazz.getName() != null && clazz.getGrade_name() != null){
			sql = "select * from tb_clazz where `name` like ? and grade_name like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+clazz.getName()+"%");
				pstmt.setString(2,"%"+clazz.getGrade_name()+"%");
				pstmt.setInt(3, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(4, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else if (clazz.getName() != null && clazz.getGrade_name() == null) {
			sql = "select * from tb_clazz where sno like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+clazz.getName()+"%");
				pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(3, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else if (clazz.getName() == null && clazz.getGrade_name() != null) {
			sql = "select * from tb_clazz where `name` like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+clazz.getGrade_name()+"%");
				pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(3, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}else {
			sql = "select * from tb_clazz limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(2, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		List<Clazz> clazzList = new ArrayList<>();

		try {
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String number = result.getString("number");
				String introducation = result.getString("introducation");
				String headmaster = result.getString("headmaster");
				String telephone = result.getString("telephone");
				String email = result.getString("email");
				String gradeName = result.getString("grade_name");
				Clazz c = new Clazz(id,name,number,introducation,headmaster,telephone,email,gradeName);
				clazzList.add(c);
			}
			pageBean.setCountSize(this.findAll().size());
			pageBean.setData(clazzList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return pageBean;
	}

	@Override
	public Clazz findById(Integer cid){
		String sql = "select * from tb_clazz where id = ?";
		Clazz clazz = new Clazz();
		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,cid);
			result = pstmt.executeQuery();
			while (result.next()){
				clazz.setId(result.getInt("id"));
				clazz.setName(result.getString("name"));
				clazz.setNumber(result.getString("number"));
				clazz.setHeadmaster(result.getString("headmaster"));
				clazz.setIntroducation(result.getString("introducation"));
				clazz.setTelephone(result.getString("telephone"));
				clazz.setEmail(result.getString("email"));
				clazz.setGrade_name(result.getString("grade_name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}
		return clazz;
	}
	@Override
	public void setClassCount(String count,String className){
		String sql = "update tb_clazz set number = ? where `name` = ?";
		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,count);
			pstmt.setString(2,className);
			pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
	}

	@Override
	public int getClassCount(String className){
		String sql = "select count(id) from tb_student where clazz_name = ?";
		int count = 0;
		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,className);
			result = pstmt.executeQuery();
			while (result.next()){
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return count;
	}

	@Override
	public boolean addClass(Clazz clazz) {
		boolean flag;
		String sql = "insert into tb_clazz values(null,?,?,?,?,?,?,?)";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,clazz.getName());
			pstmt.setString(2,clazz.getNumber());
			pstmt.setString(3,clazz.getIntroducation());
			pstmt.setString(4,clazz.getHeadmaster());
			pstmt.setString(5,clazz.getTelephone());
			pstmt.setString(6,clazz.getEmail());
			pstmt.setString(7,clazz.getGrade_name());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}

	@Override
	public boolean delClass(Integer id) {
		boolean flag;
		String sql = "delete from tb_clazz where id = ?";

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
	public boolean updateClass(Clazz clazz) {
		boolean flag;
		String sql = "update tb_clazz set `name` = ?,number = ?, introducation = ?, headmaster = ?, telephone = ?, email = ?, grade_name = ? where id = ?";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,clazz.getName());
			pstmt.setString(2,clazz.getNumber());
			pstmt.setString(3,clazz.getIntroducation());
			pstmt.setString(4,clazz.getHeadmaster());
			pstmt.setString(5,clazz.getTelephone());
			pstmt.setString(6,clazz.getEmail());
			pstmt.setString(7,clazz.getGrade_name());
			pstmt.setInt(8,clazz.getId());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}
}
