package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.other.cardAndAddress;
import org.springframework.stereotype.Component;

@Component
public interface registerRepository {
    void saveUser(User user); //保存用户
    void saveCardAndAddress(cardAndAddress cardAndAddress);  //用户第一次注册田的信息和身份证是不用写的，这里是完善
    void insertField(field field);  //根据用户手机号插入田间信息
//    void updateField(User user);  //更新田间信息  ->  作废，用户可以多次插入田的信息，不需要逗号更新了
}
