package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class User {
    private int id;    //not null auto increment
    private String user_name;   //not null
    private String user_pass;   //not null
    private String user_phone;  //not null
    private String user_card;
    private String user_cropsname; //具体作物名称
    private String user_fieldadress;
    private double user_fieldacres; //这是总亩数
    private String user_cropstype;  //类型
    private String user_cropsacres; //这是单个亩数，逗号隔开
}