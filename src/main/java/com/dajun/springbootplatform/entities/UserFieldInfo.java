package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class UserFieldInfo {
    private String user_cropstype;
    private String user_cropsacres; //这是单个亩数，逗号隔开
    private String user_cropsname;
}
