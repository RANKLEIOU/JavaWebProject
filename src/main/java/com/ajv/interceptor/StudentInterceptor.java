package com.ajv.interceptor;

import com.ajv.entity.Student;
import com.ajv.utils.PageBean;

import java.util.List;

public interface StudentInterceptor {

    List<Student> findAll();

    PageBean findAllWithPage(PageBean pageBean,Student student);

    Student findByAccount(String account);

    boolean addStudent(Student student);

    boolean delStudent(Integer id);

    boolean updateStudent(Student student);
}
