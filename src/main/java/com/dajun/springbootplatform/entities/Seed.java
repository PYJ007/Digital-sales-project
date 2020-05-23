package com.dajun.springbootplatform.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Seed {
    int seed_id;
    String seed_name;
    String seed_introduce;
    String seed_plantarea;
    String seed_method;
    Double seed_price;
    String seed_manufacturer;
    String note;
    String seed_store;
    String seed_phone;
    Date seed_productiondate;
    int seed_shelflife;
    String seed_plantnumber;
    String seed_type;
    String seed_image;
    int specialist_id;
}
