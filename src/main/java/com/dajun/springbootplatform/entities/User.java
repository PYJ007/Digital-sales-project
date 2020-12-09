package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class User {
    private int id;    //not null auto increment
    private String user_name;   //not null
    private String user_pass;   //not null
    private String user_tel;  //not null
    private String user_card;
    private String user_fieldadress;
    private Double user_fieldacres;
    private int user_type;  //类型
    private int user_favo; //这是单个亩数，逗号隔开
}