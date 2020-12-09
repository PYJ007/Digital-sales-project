package com.dajun.springbootplatform.application;


import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.entities.recommendPesticide;
import com.dajun.springbootplatform.repository.recommendRepository;

import javax.annotation.Resource;

public class findRecommend {

    @Resource
    recommendRepository recommendRepository;

    public recommendElements findElementsById(int id){
        return recommendRepository.findElementsById(id);
    }

    public recommendPesticide findPesticide(int id){
        return recommendRepository.findPesticideById(id);
    }
}
