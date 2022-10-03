package com.dajun.springbootplatform;

import com.dajun.springbootplatform.application.fertilizerRecommend;
import com.dajun.springbootplatform.application.findRecommend;
import com.dajun.springbootplatform.entities.other.recommendIdAndTel;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.repository.recommendRepository;
import com.dajun.springbootplatform.repository.specialistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

public class serviceTest {
    @RunWith(SpringRunner.class)
//加载主启动类
    @SpringBootTest(classes = SpringbootPlatformApplication.class)
    public static class AppTest {

        @Autowired
        findRecommend findRecommend;

        @Autowired
        recommendRepository recommendRepository;

        @Autowired
        specialistRepository specialistRepository;

        @Test
        public void test01(){
            List<String> names = recommendRepository.findAllFiles();
            System.out.println(names);

        }
    }
}
