package com.dajun.springbootplatform.entities.other;

import lombok.Data;

//这个实体类数据库没有，就是向mybatis传参数用的
@Data
public class cardAndAddress {
    private String user_tel;
    private String user_card;
    private String user_fieldadress;
}
