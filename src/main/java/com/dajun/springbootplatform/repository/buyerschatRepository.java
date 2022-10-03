package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.dealLobby.buyersChat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface buyerschatRepository {
    List<buyersChat> findQuistionByPhone(@Param("sellerPhone") String sellerPhone, @Param("buyerPhone") String buyerPhone);
    int findMassageStateByPhone(@Param("sellerPhone") String sellerPhone, @Param("buyerPhone") String buyerPhone);
    String findSellerPhoneByByuerPhone(String buyerPhone);
    //判断表中是否已经存在了买家与商家的联系
    Integer existSellerAndByuer(@Param("sellerPhone") String sellerPhone, @Param("buyerPhone") String buyerPhone);
    void establishSellerAndByuer(@Param("sellerPhone") String sellerPhone, @Param("buyerPhone") String buyerPhone);
    void saveAnswer(@Param("answer") String answer,@Param("state") Integer state,@Param("sellerPhone") String sellerPhone,@Param("buyerPhone") String buyerPhone);
}
