package com.dajun.springbootplatform.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Fertilizer {

    private int fertilizer_id;
    private int fertilizer_type;
    private double fertilizer_price;
    private Date fertilizer_productindate;
    private Date fertilizer_shelflife;
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
}
