package com.dajun.springbootplatform;

import com.dajun.springbootplatform.repository.recommendRepository;
import com.dajun.springbootplatform.repository.seedRepository;

import javax.annotation.Resource;


public class normalTest {

    @Resource
    public seedRepository seedRepository;

    @Resource
    public recommendRepository recommendRepository;

    public void test(){
        System.out.println(recommendRepository.findRecommendIdBySpecialistId(1));
    }

    public static void main(String[] args) {
        new normalTest().test();
    }
}
