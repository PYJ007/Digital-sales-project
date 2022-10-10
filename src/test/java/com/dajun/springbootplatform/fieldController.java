//package com.dajun.springbootplatform;
//
//import com.dajun.springbootplatform.application.computeFieldRec;
//import com.dajun.springbootplatform.entities.Fertilizer;
//import com.dajun.springbootplatform.entities.User;
//import com.dajun.springbootplatform.entities.other.recommendIdAndTel;
//import com.dajun.springbootplatform.entities.recommend;
//import com.dajun.springbootplatform.repository.MachineMapper;
//import com.dajun.springbootplatform.repository.recommendRepository;
//import com.dajun.springbootplatform.service.FertilizerServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//
//
//public class fieldController {
//    @RunWith(SpringRunner.class)
//    @SpringBootTest(classes = SpringbootPlatformApplication.class)
//    public static class AppTest {
//
//        @Resource
//        private recommendRepository recommendRepository;
//
//        @Resource
//        private com.dajun.springbootplatform.repository.userRepository userRepository;
//
//        @Resource
//        private FertilizerServiceImpl fertilizerService;
//
//        @Resource
//        private MachineMapper machineMapper;
//
//        @Test
//        public void selectRecommend() {
//            String phone = "17863203236";
//            List<recommend> recommendList = recommendRepository.findRecommendByCropsAndTime(phone,"1");
//            for (recommend recommend : recommendList) {
//
//                if (recommendRepository.recommendReadOrNot(
//                        new recommendIdAndTel(phone, recommend.getRecommend_id())) != null)
//                    recommend.setRecommend_readed(1);//判断有没有，有就是已经读啦
//            }
//            System.out.println(recommendList);
////            List<recommend> recommends = recommendRepository.findRecommendByCropsAndTime("13259680250");
//            for (recommend recommend : recommendList) {
//                if (recommend.getRecommend_type() == 2 && recommend.getStage().equals("种子露白"))
//                    System.out.println(recommend);
//                System.out.println(recommend);
//            }
//
//        }
//
//        @Test
//        public void computeTest(){
//            String phone = "17863203236";
//            List<recommend> recommendList = recommendRepository.findRecommendByCropsAndTime(phone,"1");
//            //user对象和化肥对象，为了转化出推荐
//            User user= userRepository.findByAccount(phone);
//            List<Fertilizer> fertilizers = fertilizerService.selectfertilizerList();
//            computeFieldRec computeFieldRec = new computeFieldRec();
//            for (recommend recommend : recommendList) {
//
//                if (recommendRepository.recommendReadOrNot(
//                        new recommendIdAndTel(phone, recommend.getRecommend_id()))!=null) recommend.setRecommend_readed(1);//判断有没有，有就是已经读啦
//
//                //我吐了，为了那个化肥推荐，只能利用上化肥表中的notice字段了
//                if (recommend.getRecommend_type()==2){//如果是化肥
////                    System.out.println(recommend.getRecommend_id());
////                    System.out.println("找到的结果"+recommendRepository.findElementsById(recommend.getRecommend_id()));
//                    Map<String,Object> map = computeFieldRec.selectfertilizer(
//                            user,
//                            recommendRepository.findElementsById(recommend.getRecommend_id()),
//                            fertilizers);
//                    String notice = "推荐使用化肥："+map.get("fertilizer_Name")+",预计使用化肥"+map.get("kg_need")+
//                            "KG，"+"预计花费"+map.get("money_need")+"元。";
//
//                    recommend.setNotice(notice);
//                }
//            }
//        }
//
//        @Test
//        public void findMachine(){
//            System.out.println(machineMapper.selectnongjilistbynull());
//        }
//
//
//
//    }
//}
