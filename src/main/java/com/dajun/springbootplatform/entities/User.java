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
    private String specialist_tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_card() {
        return user_card;
    }

    public void setUser_card(String user_card) {
        this.user_card = user_card;
    }

    public String getUser_fieldadress() {
        return user_fieldadress;
    }

    public void setUser_fieldadress(String user_fieldadress) {
        this.user_fieldadress = user_fieldadress;
    }

    public Double getUser_fieldacres() {
        return user_fieldacres;
    }

    public void setUser_fieldacres(Double user_fieldacres) {
        this.user_fieldacres = user_fieldacres;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUser_favo() {
        return user_favo;
    }

    public void setUser_favo(int user_favo) {
        this.user_favo = user_favo;
    }

    public String getSpecialist_tel() {
        return specialist_tel;
    }

    public void setSpecialist_tel(String specialist_tel) {
        this.specialist_tel = specialist_tel;
    }
}