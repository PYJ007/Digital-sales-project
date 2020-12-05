package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface registerRepository {
    void saveUser(User user); //保存用户
    void saveCrops(User user);  //根据用户手机号更新田间信息
    void saveCrop(User user);  //保存农业信息
}
