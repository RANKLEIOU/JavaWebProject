package com.ajv.interceptor.impl;

import com.ajv.entity.Login;
import com.ajv.utils.BaseDao;
import com.ajv.interceptor.LoginInterceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ajv
 * @Title:LoginInterceptorImpl
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/1111:17
 */
public class LoginInterceptorImpl implements LoginInterceptor {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet result;
	private BaseDao baseDao = new BaseDao();

	@Override
	public boolean login(Login login) {
		String type = login.getType();

		String sql = "";
		if (type.equals("admin")){
			sql = "select * from tb_admin where name = ? and password = ?";
		} else if (type.equals("teacher")) {
			sql = "select * from tb_teacher where name = ? and password = ?";
		} else if (type.equals("student")) {
			sql = "select * from tb_student where name = ? and password = ?";
		}

		try {
			conn = baseDao.getCon();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,login.getAccount());
			pstmt.setString(2,login.getPassword());
			result = pstmt.executeQuery();
			return result.next();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			baseDao.closeConnection(conn,pstmt,result);
		}
	}

}
