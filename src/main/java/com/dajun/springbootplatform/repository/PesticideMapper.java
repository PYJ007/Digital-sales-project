package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.Pesticide;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesticideMapper {
    List<Pesticide> selectnongyaolistbynull();

    @Select("select * from pesticide where pesticide_id > 21000")
    List<Pesticide> selectAllNotPassPesticide();

    @Update("update pesticide set pesticide_id=#{newId} where pesticide_id=#{id}")
    void updatePesticideId(@Param("id") int id, @Param("newId") String newId);

    @Select("select * from pesticide where pesticide_id=#{id}")
    Pesticide selectPesticideById(@Param("id") int id);

    void insertpesticide(Pesticide pesticide);
    int deletonepesticide(Integer pesticideId);
}
