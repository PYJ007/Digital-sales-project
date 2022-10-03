package com.dajun.springbootplatform.entities.dealLobby;

public class cargo {
    private int c_year;
    private int c_month;
    private int c_id;
    private String user_phone;
    private String c_name;

    public cargo() {
    }

    public cargo(int c_year, int c_month, int c_id, String user_phone, String c_name) {
        this.c_year = c_year;
        this.c_month = c_month;
        this.c_id = c_id;
        this.user_phone = user_phone;
        this.c_name = c_name;
    }

    public int getC_year() {
        return c_year;
    }

    public void setC_year(int c_year) {
        this.c_year = c_year;
    }

    public int getC_month() {
        return c_month;
    }

    public void setC_month(int c_month) {
        this.c_month = c_month;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
