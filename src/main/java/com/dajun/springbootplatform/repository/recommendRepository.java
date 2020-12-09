package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.other.specialistAndSeedId;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.entities.recommendPesticide;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface recommendRepository {
    //这里是查询五个推荐
    List<recommend> findBySeedIdAndSpecialistIdLimit(specialistAndSeedId specialistAndSeedId);
    //删除ID
    void deleteByRecommendId(int recommend_id);
    //插入推荐
    void insertBySeedId(recommend recommend);
    int findRecommendIdBySpecialistId(int specialistId);
    void saveElements(recommendElements recommendElements);
    void savePesticide(recommendPesticide recommendPesticide);
    List<recommend> findBySeedIdAndSpecialistId(specialistAndSeedId specialistAndSeedId);
    //通过id获取推荐的化肥元素
    recommendElements findElementsById(int id);
    //通过ID获取推荐的预防疾病
    recommendPesticide findPesticideById(int id);
    //通过用户作物id和时间比较筛选信息,按时间降序
    recommend findRecommendByCropsAndTime(int cropId);

}
