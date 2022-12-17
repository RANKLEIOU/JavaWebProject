package com.ajv.interceptor.impl;

import com.ajv.entity.Student;
import com.ajv.utils.BaseDao;
import com.ajv.interceptor.StudentInterceptor;
import com.ajv.utils.PageBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ajv
 */
public class StudentInterceptorImpl implements StudentInterceptor {

    private final BaseDao baseDao = new BaseDao();
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet result;

    @Override
    public List<Student> findAll(){
        String sql = "select * from tb_student";

        List<Student> studentList = new ArrayList<>();

        try {
            connection = baseDao.getCon();
            pstmt = connection.prepareStatement(sql);
            result = pstmt.executeQuery();
            while (result.next()){
                Integer id = result.getInt("id");
                String sno = result.getString("sno");
                String name = result.getString("name");
                String gender = result.getString("gender");
                String password = result.getString("password");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String address = result.getString("address");
                String introducation = result.getString("introducation");
                String className = result.getString("clazz_name");
                Student student = new Student(id,sno,name,gender,password,email,telephone,address,introducation,className);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            baseDao.closeConnection(connection,pstmt,result);
        }

        return studentList;
    }

    @Override
    public PageBean findAllWithPage(PageBean pageBean,Student student){
        String sql;
        if (!"".equals(student.getClazz_name()) && student.getName() != null){
            sql = "select * from tb_student where clazz_name = ? and `name` like ? limit  ?,?";
            try {
                connection = baseDao.getCon();
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,student.getClazz_name());
                pstmt.setString(2,"%"+student.getName()+"%");
                pstmt.setInt(3, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
                pstmt.setInt(4, pageBean.getLimitSize());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (!"".equals(student.getClazz_name()) && student.getName() == null) {
            sql = "select * from tb_student where clazz_name = ? limit  ?,?";
            try {
                connection = baseDao.getCon();
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,student.getClazz_name());
                pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
                pstmt.setInt(3, pageBean.getLimitSize());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("".equals(student.getClazz_name()) && student.getName() != null) {
            sql = "select * from tb_student where `name` like ? limit  ?,?";
            try {
                connection = baseDao.getCon();
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,"%"+student.getName()+"%");
                pstmt.setInt(2, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
                pstmt.setInt(3, pageBean.getLimitSize());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            sql = "select * from tb_student limit  ?,?";
            try {
                connection = baseDao.getCon();
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, (pageBean.getCurrPage()-1)*pageBean.getLimitSize());
                pstmt.setInt(2, pageBean.getLimitSize());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        List<Student> studentList = new ArrayList<>();

        try {
            result = pstmt.executeQuery();
            while (result.next()){
                Integer id = result.getInt("id");
                String sno = result.getString("sno");
                String name = result.getString("name");
                String gender = result.getString("gender");
                String password = result.getString("password");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String address = result.getString("address");
                String introducation = result.getString("introducation");
                String className = result.getString("clazz_name");
                Student s = new Student(id,sno,name,gender,password,email,telephone,address,introducation,className);
                studentList.add(s);
            }
            pageBean.setCountSize(this.findAll().size());
            pageBean.setData(studentList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            baseDao.closeConnection(connection,pstmt,result);
        }

        return pageBean;
    }

    public Student findByAccount(String account){
        String sql = "select * from tb_student where `name` = ?";
        Student student = new Student();
        try {
            connection = baseDao.getCon();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,account);
            result = pstmt.executeQuery();
            while (result.next()){
                student.setId(result.getInt("id"));
                student.setSno(result.getString("sno"));
                student.setName(result.getString("name"));
                student.setGender(result.getString("gender"));
                student.setPassword(result.getString("password"));
                student.setEmail(result.getString("email"));
                student.setTelephone(result.getString("telephone"));
                student.setAddress(result.getString("address"));
                student.setIntroducation(result.getString("introducation"));
                student.setClazz_name(result.getString("clazz_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            baseDao.closeConnection(connection,pstmt,result);
        }
        return student;
    }

    @Override
    public boolean addStudent(Student student) {
        boolean flag;
        String sql = "insert into tb_student VALUES(null,?,?,?,'123456',?,?,?,?,?)";

        try {
            connection = baseDao.getCon();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,student.getSno());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getGender());
            pstmt.setString(4,student.getEmail());
            pstmt.setString(5,student.getTelephone());
            pstmt.setString(6,student.getAddress());
            pstmt.setString(7,student.getIntroducation());
            pstmt.setString(8,student.getClazz_name());
            flag = pstmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            baseDao.closeConnection(connection,pstmt,null);
        }
        return flag;
    }

    @Override
    public boolean delStudent(Integer id) {
        boolean flag;
        String sql = "delete from tb_student where id = ?";

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
    public boolean updateStudent(Student student) {
        boolean flag;
        String sql;
        if (student.getSno() == null && student.getClazz_name() == null){
            sql = "update tb_student set `name` = ?,gender = ?, password = ?, email = ?, telephone = ?, address = ?,introducation = ? where id = ?";
            try {
                connection = baseDao.getCon();
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,student.getName());
                pstmt.setString(2,student.getGender());
                pstmt.setString(3,student.getPassword());
                pstmt.setString(4,student.getEmail());
                pstmt.setString(5,student.getTelephone());
                pstmt.setString(6,student.getAddress());
                pstmt.setString(7,student.getIntroducation());
                pstmt.setInt(8,student.getId());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }else{
            sql = "update tb_student set sno = ?, `name` = ?,gender = ?, password = ?, email = ?, telephone = ?, address = ?,introducation = ?,clazz_name = ? where id = ?";
            try {
                connection = baseDao.getCon();
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,student.getSno());
                pstmt.setString(2,student.getName());
                pstmt.setString(3,student.getGender());
                pstmt.setString(4,student.getPassword());
                pstmt.setString(5,student.getEmail());
                pstmt.setString(6,student.getTelephone());
                pstmt.setString(7,student.getAddress());
                pstmt.setString(8,student.getIntroducation());
                pstmt.setString(9,student.getClazz_name());
                pstmt.setInt(10,student.getId());
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
