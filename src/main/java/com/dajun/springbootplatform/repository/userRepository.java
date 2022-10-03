package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface userRepository{
    List<User> findAll();
    List<User> findBuyerBySellerPhone(String phone);
    List<User> findSellerBybuyerPhone(String phone);
    User findByAccount(String account);
    void save(User user);
    void deleteById(String user_tel);
    void upDate(User user);
    String findName(String phone);
    //根据用户的电话查找专家的电话
    String findSpecialistTel(String phone);
    //根据专家电话查找用户电话
    String findUserTel(String phone);
    //根据专家电话获取关联的用户名
    List<String> findUserName(String phone);
    User findByName(String name);
    //根据用户名查找用户电话
    String findPhoneByName(String name);
    //根据用户电话和种子ID建立浏览记录
    void insertBrowsing(String user_phone,int seed_id);
}
