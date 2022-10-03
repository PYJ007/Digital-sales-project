package com.dajun.springbootplatform.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Fertilizer {

    private int fertilizer_id;
    private int fertilizer_type;
    private double fertilizer_price;
    private Date fertilizer_productiondate;
    private int fertilizer_shelflife;
    private String fertilizer_name;
    private String fertilizer_n;
    private String fertilizer_p;
    private String fertilizer_k;
    private String fertilizer_other;
    private String fertilizer_manufacturer;
    private String fertilizer_instructions;
    private String fertilizer_phone;
    private String fertilizer_address;
    private String fertilizer_moderatecrop;
    private Date recommend_data;

    public int getFertilizer_id() {
        return fertilizer_id;
    }

    public void setFertilizer_id(int fertilizer_id) {
        this.fertilizer_id = fertilizer_id;
    }

    public int getFertilizer_type() {
        return fertilizer_type;
    }

    public void setFertilizer_type(int fertilizer_type) {
        this.fertilizer_type = fertilizer_type;
    }

    public double getFertilizer_price() {
        return fertilizer_price;
    }

    public void setFertilizer_price(double fertilizer_price) {
        this.fertilizer_price = fertilizer_price;
    }

    public Date getFertilizer_productiondate() {
        return fertilizer_productiondate;
    }

    public void setFertilizer_productiondate(Date fertilizer_productiondate) {
        this.fertilizer_productiondate = fertilizer_productiondate;
    }

    public int getFertilizer_shelflife() {
        return fertilizer_shelflife;
    }

    public void setFertilizer_shelflife(int fertilizer_shelflife) {
        this.fertilizer_shelflife = fertilizer_shelflife;
    }

    public String getFertilizer_name() {
        return fertilizer_name;
    }

    public void setFertilizer_name(String fertilizer_name) {
        this.fertilizer_name = fertilizer_name;
    }

    public String getFertilizer_n() {
        return fertilizer_n;
    }

    public void setFertilizer_n(String fertilizer_n) {
        this.fertilizer_n = fertilizer_n;
    }

    public String getFertilizer_p() {
        return fertilizer_p;
    }

    public void setFertilizer_p(String fertilizer_p) {
        this.fertilizer_p = fertilizer_p;
    }

    public String getFertilizer_k() {
        return fertilizer_k;
    }

    public void setFertilizer_k(String fertilizer_k) {
        this.fertilizer_k = fertilizer_k;
    }

    public String getFertilizer_other() {
        return fertilizer_other;
    }

    public void setFertilizer_other(String fertilizer_other) {
        this.fertilizer_other = fertilizer_other;
    }

    public String getFertilizer_manufacturer() {
        return fertilizer_manufacturer;
    }

    public void setFertilizer_manufacturer(String fertilizer_manufacturer) {
        this.fertilizer_manufacturer = fertilizer_manufacturer;
    }

    public String getFertilizer_instructions() {
        return fertilizer_instructions;
    }

    public void setFertilizer_instructions(String fertilizer_instructions) {
        this.fertilizer_instructions = fertilizer_instructions;
    }

    public String getFertilizer_phone() {
        return fertilizer_phone;
    }

    public void setFertilizer_phone(String fertilizer_phone) {
        this.fertilizer_phone = fertilizer_phone;
    }

    public String getFertilizer_address() {
        return fertilizer_address;
    }

    public void setFertilizer_address(String fertilizer_address) {
        this.fertilizer_address = fertilizer_address;
    }

    public String getFertilizer_moderatecrop() {
        return fertilizer_moderatecrop;
    }

    public void setFertilizer_moderatecrop(String fertilizer_moderatecrop) {
        this.fertilizer_moderatecrop = fertilizer_moderatecrop;
    }

    public Date getRecommend_data() {
        return recommend_data;
    }

    public void setRecommend_data(Date recommend_data) {
        this.recommend_data = recommend_data;
    }
}
