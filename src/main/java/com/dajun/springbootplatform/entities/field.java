package com.dajun.springbootplatform.entities;


import lombok.Data;

@Data
public class field {
    private String user_tel;
    private String user_cropsname;
    private String user_cropstype;
    private Double user_cropsacres;
    private int field_id;
    private int use_time;//农田使用次数
}
