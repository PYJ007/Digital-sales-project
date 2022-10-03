package com.dajun.springbootplatform.controller;


import com.dajun.springbootplatform.application.Converse;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.repository.recommendRepository;
import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.repository.specialistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class testController {

    @Resource
    private seedRepository seedRepository;

    @Resource
    private recommendRepository recommendRepository;

    @Resource
    private specialistRepository specialistRepository;

    @RequestMapping("/test")
    String test1(){
        List<recommend> recommendList = recommendRepository.findRecommendByCropsAndTime("13259680250");
        for(recommend recommend:recommendList){
            System.out.println(recommend.getSpecialist_id());
            System.out.println(new Converse().findSpecialistName(recommend.getSpecialist_id()));
        }
//        for (String name:str){
//            System.out.println(name);
//            System.out.println("!!!");
//        }
        return "";
    }
}
