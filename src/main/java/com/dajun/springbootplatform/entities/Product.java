package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class Product {
    private int product_id;
    private String product_name;
    private String product_special;
    private String product_img;
}
