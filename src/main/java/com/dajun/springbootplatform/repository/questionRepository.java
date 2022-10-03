package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.connect.questions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface questionRepository {
    //根据用户的电话查询用户与专家的对话信息
    List<questions> findquestionByPhone(String userPhone);
    //保存提问
    void saveQuestion(@Param("question")String question, @Param("q_state")Integer q_state, @Param("user_tel")String user_tel);
    void setState(Integer id);
    String findPhoneById(Integer id);
    void deleteQuestion(Integer id);
    String getStateNumByPhone(String phone);
}
