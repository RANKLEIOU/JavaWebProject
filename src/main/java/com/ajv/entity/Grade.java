package com.ajv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: sms
 * @description: 年级及年级主任信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    //年级信息
    private Integer id;             //年级ID
    private String name;            //年级名称
    private String introducation;    //年级介绍
    //年级主任信息
    private String manager;         //年级主任姓名
    private String email;           //年级主任邮箱
    private String telephone;       //年级主任电话

}
