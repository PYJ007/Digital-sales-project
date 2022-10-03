package com.dajun.springbootplatform.entities;

import lombok.Data;

import java.util.Date;


@Data
public class Seed {
    private int seed_id;
    private String seed_name;
    private String seed_introduce;
    private String seed_plantarea;
    private String seed_method;
    private Double seed_price;
    private String seed_manufacturer;
    private String seed_note;
    private String seed_store;
    private String seed_phone;
    private Date seed_productiondate;
    private int seed_shelflife;
    private String seed_plantnumber;
    private String seed_type;
//    private String seed_image;
    private byte[] seed_image;
    private int specialist_id;
    private int seed_state;

}
