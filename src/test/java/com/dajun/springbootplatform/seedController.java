package com.dajun.springbootplatform;

import com.dajun.springbootplatform.application.computeFieldRec;
import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.other.recommendIdAndTel;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.repository.*;
import com.dajun.springbootplatform.service.FertilizerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class seedController {
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringbootPlatformApplication.class)
    public static class AppTest {

        @Resource
        private recommendRepository recommendRepository;

        @Resource
        private com.dajun.springbootplatform.repository.userRepository userRepository;

        @Resource
        private FertilizerServiceImpl fertilizerService;

        @Resource
        private seedRepository seedRepository;

        @Test
        public void findSeed(){
            List<Seed> seedList = seedRepository.findThreeSeed();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            //种子的时间需要处理一下
            for (int i=0;i<seedList.size();i++){
                Seed seed = seedList.get(i);
                seed.setSeed_manufacturer(ft.format(seedList.get(i).getSeed_productiondate()));
                seedList.set(i,seed);

            }
            for (Seed seed:seedList){
//                System.out.println(seed.getSeed_productiondate());
                System.out.println(seed.getSeed_manufacturer());
            }
        }

        @Test
        public void insertSeed(){
            Seed seed = new Seed();
            seed.setSeed_name("中宁枸杞");
            seed.setSpecialist_id(1);
            seed.setSeed_type("枸杞");
            seed.setSeed_method("旱栽");
//            seed.setSeed_image("..");
            seed.setSeed_introduce("没什么好介绍的");
            seed.setSeed_phone("110");
            seed.setSeed_plantarea("中宁县");
            seed.setSeed_note("");
            seed.setSeed_plantnumber("每亩1颗");
            seed.setSeed_manufacturer("宁夏农科院");
            seedRepository.saveSeedInfo(seed);
//            insert into seed
//                    ( seed_name, seed_introduce, seed_plantarea, seed_method, seed_price, seed_note,
//                            seed_productiondate, Seed_shelflife, seed_plantnumber, seed_type, seed_image,
//                            specialist_id,seed_manufacturer,seed_store)
//            values(#{seed_name},#{seed_introduce},#{seed_plantarea},#{seed_method},0.0,#{seed_note},
//            '1999-11-11',2,#{seed_plantnumber},#{seed_type},#{seed_image},#{specialist_id},#{seed_manufacturer},'default')

        }
    }
}
