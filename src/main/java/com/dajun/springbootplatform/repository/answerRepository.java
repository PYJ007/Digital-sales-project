package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.connect.answers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface answerRepository {
    //根据用户的电话查询用户与专家的对话信息
    List<answers> findAnswerByPhone(String userPhone);
    void saveAnswer(@Param("answer") String answer,@Param("a_state") Integer a_state,@Param("user_tel") String userTel);
    void setState(Integer id);
    String findPhoneById(Integer id);
    void deleteAnswer(Integer id);
}
