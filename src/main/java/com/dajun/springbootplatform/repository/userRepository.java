package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface userRepository{
    public List<User> findAll();
    public User findByAccount(String account);
    public void save(User user);
    public void deleteById(long id);
    public void upDate(User user);
}
