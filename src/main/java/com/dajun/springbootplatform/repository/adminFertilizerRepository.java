package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Fertilizer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface adminFertilizerRepository {

    @Select("select * from fertilizer where fertilizer_id > 21000")
    List<Fertilizer> selectAllNotPassFertilizer();

    @Update("update fertilizer set fertilizer_id=#{newId} where fertilizer_id=#{id}")
    void updateFertilizerId(@Param("id") int id, @Param("newId") String newId);

    @Select("select * from fertilizer where fertilizer_id=#{id}")
    Fertilizer selectFertilizerById(@Param("id") int id);
}
