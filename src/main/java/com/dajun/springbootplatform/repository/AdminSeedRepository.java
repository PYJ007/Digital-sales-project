package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Seed;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminSeedRepository {

    @Select("select * from seed where seed_id > 21000")
    List<Seed> selectAllNotPassSeeds();

    @Update("update seed set seed_id=#{newId} where seed_id=#{id}")
    void updateSeedId(@Param("id") int id, @Param("newId") String newId);
}
