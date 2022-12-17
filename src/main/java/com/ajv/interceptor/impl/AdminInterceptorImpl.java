package com.ajv.interceptor.impl;

import com.ajv.entity.Admin;
import com.ajv.interceptor.AdminInterceptor;
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
 * @Title:adminInterceptorImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/229:44
 */
public class AdminInterceptorImpl implements AdminInterceptor {
	private final BaseDao baseDao = new BaseDao();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet result;

	@Override
	public List<Admin> findAll(){
		String sql = "select * from tb_admin";

		List<Admin> adminList = new ArrayList<>();

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String gender = result.getString("gender");
				String password = result.getString("password");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String address = result.getString("address");
				Admin admin = new Admin(id,name,gender,password,email,telephone,address);
				adminList.add(admin);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return adminList;
	}

	@Override
	public PageBean findByPage(PageBean pageBean, Admin admin){
		String sql;
		if (admin.getName() != null){
			sql = "select * from tb_admin where `name` like ? limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+admin.getName()+"%");
				pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(3, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			sql = "select * from tb_admin limit  ?,?";
			try {
				connection = baseDao.getCon();
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
				pstmt.setInt(2, pageBean.getLimitSize());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		List<Admin> adminList = new ArrayList<>();

		try {
			result = pstmt.executeQuery();
			while (result.next()){
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String gender = result.getString("gender");
				String password = result.getString("password");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String address = result.getString("address");
				Admin t = new Admin(id,name,gender,password,email,telephone,address);
				adminList.add(t);
			}
			pageBean.setCountSize(this.findAll().size());
			pageBean.setData(adminList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}

		return pageBean;
	}

	public Admin findByAccount(String account){
		String sql = "select * from tb_admin where `name` = ?";
		Admin admin = new Admin();
		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,account);
			result = pstmt.executeQuery();
			while (result.next()){
				admin.setId(result.getInt("id"));
				admin.setName(result.getString("name"));
				admin.setGender(result.getString("gender"));
				admin.setPassword(result.getString("password"));
				admin.setEmail(result.getString("email"));
				admin.setTelephone(result.getString("telephone"));
				admin.setAddress(result.getString("address"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			baseDao.closeConnection(connection,pstmt,result);
		}
		return admin;
	}

	@Override
	public boolean addAdmin(Admin admin) {
		boolean flag;
		String sql = "insert into tb_admin values(null,?,?,'123456',?,?,?)";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,admin.getName());
			pstmt.setString(2,admin.getGender());
			pstmt.setString(3,admin.getEmail());
			pstmt.setString(4,admin.getTelephone());
			pstmt.setString(5,admin.getAddress());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}

	@Override
	public boolean delAdmin(Integer id) {
		boolean flag;
		String sql = "delete from tb_admin where id = ?";

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
	public boolean updateAdmin(Admin admin) {
		boolean flag;
		String sql = "update tb_admin set `name` = ?,gender = ?, password = ?, email = ?, telephone = ?, address = ? where id = ?";

		try {
			connection = baseDao.getCon();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,admin.getName());
			pstmt.setString(2,admin.getGender());
			pstmt.setString(3,admin.getPassword());
			pstmt.setString(4,admin.getEmail());
			pstmt.setString(5,admin.getTelephone());
			pstmt.setString(6,admin.getAddress());
			pstmt.setInt(7,admin.getId());
			flag = pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(connection,pstmt,null);
		}
		return flag;
	}
}
