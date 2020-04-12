package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String user_name;
    private String user_pass;
    private String user_phone;
    private String user_card;
    private String user_fieldadress;
    private double user_fieldacres;
    private Integer field_id;
}