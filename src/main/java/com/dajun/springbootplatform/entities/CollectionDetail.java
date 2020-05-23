package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class CollectionDetail {

    private Product product;
    private String date;



    public CollectionDetail(Product product, String date) {
        this.product = product;
        this.date = date;
    }
}
