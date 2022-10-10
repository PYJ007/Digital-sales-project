package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.recommend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface historicalProcessRepository {
    List<recommend> findRecommendsByGroupId(String groupId);
    Integer findSeedIdByGroupId(String groupId);
    //根据recommendId和groupId找到用户执行专家推荐的时间
    Date findExcuteTime(@Param("recommendId") Integer recommendId,@Param("groupId") String groupId);
    String findHistoryOrnot(String groupId);
}
