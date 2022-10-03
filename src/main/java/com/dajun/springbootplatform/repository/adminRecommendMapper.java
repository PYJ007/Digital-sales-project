package com.dajun.springbootplatform.repository;


import com.dajun.springbootplatform.entities.admin;
import com.dajun.springbootplatform.entities.recommend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface adminRecommendMapper {
    admin selectAdminByName(String admin_name);
    List<recommend> selectRecommendBySeedSpecialistAndSowMethod(@Param("seed_id") int seed_id,
                                                                @Param("specialist_id") int specialist_id,
                                                                @Param("sowMethod") int sowMethod);
}
