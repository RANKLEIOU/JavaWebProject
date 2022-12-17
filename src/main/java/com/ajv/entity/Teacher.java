package com.ajv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: ajv
 * @description: 教师信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private Integer id;
    private String tno;
    private String name;
    private String gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String clazz_name;

}