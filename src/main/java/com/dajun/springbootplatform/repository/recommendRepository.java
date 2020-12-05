package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.other.specialistAndSeedId;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.entities.recommendPesticide;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface recommendRepository {
    List<recommend> findBySeedIdAndSpecialistIdLimit(specialistAndSeedId specialistAndSeedId);
    void deleteByRecommendId(int recommend_id);
    void insertBySeedId(recommend recommend);
    int findRecommendIdBySpecialistId(int specialistId);
    void saveElements(recommendElements recommendElements);
    void savePesticide(recommendPesticide recommendPesticide);
    List<recommend> findBySeedIdAndSpecialistId(specialistAndSeedId specialistAndSeedId);

}
