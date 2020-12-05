package com.dajun.springbootplatform.entities;

import java.util.Date;

public class buy {
    private int user_id;
    private int buy_type;
    private String objects_name;
    private int buy_num;
    private Date buy_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(int buy_type) {
        this.buy_type = buy_type;
    }

    public String getObjects_name() {
        return objects_name;
    }

    public void setObjects_name(String objects_name) {
        this.objects_name = objects_name;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }

    public Date getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(Date buy_time) {
        this.buy_time = buy_time;
    }
}
