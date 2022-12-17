package com.ajv.service.impl;

import com.ajv.entity.Student;
import com.ajv.interceptor.ClassInterceptor;
import com.ajv.interceptor.StudentInterceptor;
import com.ajv.interceptor.impl.ClassInterceptorImpl;
import com.ajv.interceptor.impl.StudentInterceptorImpl;
import com.ajv.service.StudentService;
import com.ajv.utils.PageBean;

public class StudentServiceImpl implements StudentService {

    private StudentInterceptor studentInterceptor = new StudentInterceptorImpl();
    private ClassInterceptor classInterceptor = new ClassInterceptorImpl();

    @Override
    public PageBean findAllWithPage(PageBean pageBean,Student student){
        return studentInterceptor.findAllWithPage(pageBean,student);
    }

    @Override
    public Student findByAccount(String account){
        return studentInterceptor.findByAccount(account);
    }

    @Override
    public void addStudent(Student student) {
        studentInterceptor.addStudent(student);
        String count = String.valueOf(classInterceptor.getClassCount(student.getClazz_name()));
        classInterceptor.setClassCount(count,student.getClazz_name());
    }

    @Override
    public void delStudent(Integer id) {
        studentInterceptor.delStudent(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentInterceptor.updateStudent(student);
    }
}
