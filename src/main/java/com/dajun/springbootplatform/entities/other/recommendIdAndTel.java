package com.dajun.springbootplatform.entities.other;


import lombok.Data;

@Data
public class recommendIdAndTel {
    private String user_tel;
    private int recommend_id;

    public recommendIdAndTel(String user_tel, int recommend_id) {
        this.user_tel = user_tel;
        this.recommend_id = recommend_id;
    }
}
