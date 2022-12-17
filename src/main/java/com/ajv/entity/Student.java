package com.ajv.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Student {
    private int id;
    private String sno;
    private String name;
    private String gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String introducation;
    private String clazz_name;
}
