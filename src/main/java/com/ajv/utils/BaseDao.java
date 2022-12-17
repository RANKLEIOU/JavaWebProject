package com.ajv.utils;

import java.sql.*;

public class BaseDao {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/edu_info?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static final String username = "root";
	private static final String password = "123456";

	public Connection getCon(){
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}

		return con;
	}

	public void closeConnection(Connection con,PreparedStatement pstmt,ResultSet resultSet){

		try {
			if (con != null) {
				con.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
			if (resultSet != null){
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
