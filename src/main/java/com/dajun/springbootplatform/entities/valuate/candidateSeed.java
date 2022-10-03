package com.dajun.springbootplatform.entities.valuate;

import lombok.Data;


@Data
public class candidateSeed {
    private int seed_id;
    private String seed_name;
    private String seed_introduce;
    private String seed_plantarea;
    private String seed_method;
    private double seed_price;
    private String seed_manufacturer;
    private String seed_productiondate;
    private byte[] seed_image;
    private String user_phone;
    private String seed_type;
    private byte[] certificate;
    private int state;
    private String reason;
}
