package com.dajun.springbootplatform.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Machine {
    private Integer machine_id;

    private String machine_name;

    private String machine_function;

    private String machine_manufacturer;

    private String machine_phone;

    private Double machine_price;

    private String machine_address;

    private String machine_type;

    private LocalDate recommend_data;

    private String machine_moderatecrop;
}
