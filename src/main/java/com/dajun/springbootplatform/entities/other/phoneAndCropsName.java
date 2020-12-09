package com.dajun.springbootplatform.entities.other;

import lombok.Data;

//同样只是mybatis传输用的
@Data
public class phoneAndCropsName {
    String user_tel;
    String user_cropsname;

    public phoneAndCropsName(String user_tel, String user_cropsname) {
        this.user_tel = user_tel;
        this.user_cropsname = user_cropsname;
    }
}
