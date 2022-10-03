package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Specialist;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.other.cardAndAddress;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface registerRepository {
    void saveUser(User user); //保存用户
    void saveCardAndAddress(cardAndAddress cardAndAddress);  //用户第一次注册田的信息和身份证是不用写的，这里是完善
    void insertField(field field);  //根据用户手机号插入田间信息
//    void updateField(User user);  //更新田间信息  ->  作废，用户可以多次插入田的信息，不需要逗号更新了
    void saveSpecialist(Specialist specialist);

    @Update("update specialist set specialist_id=#{newId} where specialist_id=#{id}")
    void updateSpecialistId(@Param("id") int id, @Param("newId") String newId);
}
