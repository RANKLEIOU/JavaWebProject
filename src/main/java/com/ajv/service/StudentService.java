package com.ajv.service;

import com.ajv.entity.Student;
import com.ajv.utils.PageBean;

public interface StudentService {

    PageBean findAllWithPage(PageBean pageBean,Student student);

    Student findByAccount(String account);

    void addStudent(Student student);

    void delStudent(Integer id);

    void updateStudent(Student student);
}
