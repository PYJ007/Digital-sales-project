package com.dajun.springbootplatform.entities.other;

import lombok.Data;

//播种方式和种子ID，为了向我的田展示信息用的
@Data
public class sowMethodAndSeedId {
    int seed_id;
    int sowmethod;

    public sowMethodAndSeedId(int seed_id, int sowmethod) {
        this.seed_id = seed_id;
        this.sowmethod = sowmethod;
    }
}
