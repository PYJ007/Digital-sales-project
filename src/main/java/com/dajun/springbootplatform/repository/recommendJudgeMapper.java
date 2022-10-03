package com.dajun.springbootplatform.repository;



import com.dajun.springbootplatform.entities.other.recommendInfo;
import com.dajun.springbootplatform.entities.recommend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface recommendJudgeMapper {
    @Select("select distinct recommend_id,specialist_id,seed_id,sowmethod from recommendjude where judge=0 group by sowmethod")
    List<recommendInfo> findRecommendInfo();

    @Select("select * from recommendjude where specialist_id=#{specialist_id} and seed_id=#{seed_id} and sowmethod=#{sowmethod}")
    List<recommend> findRecByIDAndSow(@Param("specialist_id") int specialist_id,
                                      @Param("seed_id") int seed_id,
                                      @Param("sowmethod") int sowmethod);

    @Update("update recommendjude set judge=1 where specialist_id=#{specialist_id} and seed_id=#{seed_id} and sowmethod=#{sowmethod}")
    void passRecommend(@Param("specialist_id") int specialist_id,
                       @Param("seed_id") int seed_id,
                       @Param("sowmethod") int sowmethod);

    @Insert("insert into recommend" +
            "(recommend_id,specialist_id,recommend_time,recommend_endtime,seed_id,recommend_type,recommend_readed,detail,notice,stage,sowmethod) " +
            "select recommend_id,specialist_id,recommend_time,recommend_endtime,seed_id,recommend_type,recommend_readed,detail,notice,stage,sowmethod " +
            "from recommendjude where judge=1 and recommend_id not in (select recommend_id from recommend);")
    void insertToRecommend();
}
