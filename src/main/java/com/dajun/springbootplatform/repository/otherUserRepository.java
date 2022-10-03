package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.OtherUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface otherUserRepository {

    void insertOtherUser(OtherUser otherUser);

    @Select("select * from otheruser where phone =#{phone}")
    OtherUser selectAUser(@Param("phone") String  phone);
}
