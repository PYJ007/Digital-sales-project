package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface userRepository{
    List<User> findAll();
    User findByAccount(String account);
    void save(User user);
    void deleteById(long id);
    void upDate(User user);
    String findName(String phone);
}
