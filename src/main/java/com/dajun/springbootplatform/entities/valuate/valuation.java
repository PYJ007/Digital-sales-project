package com.dajun.springbootplatform.entities.valuate;

import lombok.Data;

@Data
public class valuation {
    private Integer seed_id;
    private Integer purity;
    private Integer neatness;
    private Integer germinationrate;
    private Integer wet;
    private String rank;
    private String grade;

    public valuation() {
    }

    public valuation(Integer seed_id, Integer purity, Integer neatness, Integer germinationrate, Integer wet, String rank, String grade) {
        this.seed_id = seed_id;
        this.purity = purity;
        this.neatness = neatness;
        this.germinationrate = germinationrate;
        this.wet = wet;
        this.rank = rank;
        this.grade = grade;
    }
}
