package com.dajun.springbootplatform.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Pesticide {
    private Integer pesticide_id;

    private String pesticide_name;

    private String pesticide_composition;

    private String pesticide_dosage;

    private String pesticide_content;

    private String pesticide_predisease;

    private String pesticide_instructions;

    private Double pesticide_price;

    private String pesticide_manufacturer;

    private String pesticide_licensenumber;

    private String pesticide_address;

    private String  pesticide_productiondate;

    private String pesticide_moderatecrop;

    private String pesticide_virulence;

    private String pesticide_note;

    private String pesticide_phone;

    private Integer pesticide_Shelflife;

    private String recommend_data;
}
