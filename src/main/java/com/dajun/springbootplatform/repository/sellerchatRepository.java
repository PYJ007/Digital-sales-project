package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.dealLobby.sellerChat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface sellerchatRepository {
    List<sellerChat> findAnswerByPhone(@Param("sellerPhone") String sellerPhone,@Param("buyerPhone") String buyerPhone);
    void saveAnswer(@Param("answer") String answer,@Param("state") Integer state,@Param("sellerPhone") String sellerPhone,@Param("buyerPhone") String buyerPhone);
    void setState(Integer id);
    String findPhoneById(Integer id);
    void deleteAnswer(Integer id);
    String findBuyerPhoneBySellerPhone(String sellerPhone);
    int findMassageStateByPhone(@Param("sellerPhone") String sellerPhone, @Param("buyerPhone") String buyerPhone);
}
