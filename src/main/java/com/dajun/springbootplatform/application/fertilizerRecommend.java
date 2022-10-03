package com.dajun.springbootplatform.application;

import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.repository.fieldRepository;
import com.dajun.springbootplatform.repository.recommendRepository;
import com.dajun.springbootplatform.repository.userRepository;
import com.dajun.springbootplatform.service.FertilizerServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Component
//@RestController
//@RequestMapping("/fertilizerrecommends")
public class fertilizerRecommend {

    @Resource
    recommendRepository recommendRepository;
    @Resource
    userRepository userRepository;
    @Resource
    FertilizerServiceImpl fertilizerService;


    //根据推荐id获取农药信息
//    @GetMapping("/bestferilizer")
//    Map<String, Object> selectfertilizer(@RequestParam String userTel, @RequestParam String recommendId){
    public Map<String, Object> selectfertilizer(String userTel,Integer recommendId) {

        //通过手机号码找到对应的用户
        User userbean = userRepository.findByAccount(userTel);
        //找到相应的推荐信息
        recommendElements recommendfertilizerbean = recommendRepository.findElementsById(recommendId);
        List<Fertilizer> fertilizerbean = fertilizerService.selectfertilizerList();

        Map<String, Object> fertilizerneedMap = new HashMap<>();
        System.out.println(recommendfertilizerbean);
        System.out.println(userbean);
        fertilizerbean.forEach(System.out::println);
        Fertilizer solve = solve(fertilizerbean, recommendfertilizerbean);

        //用户亩数
        BigDecimal userfildacres = new BigDecimal(userbean.getUser_fieldacres().toString());
        //专家推荐
        BigDecimal recommend_N_needed = new BigDecimal(recommendfertilizerbean.getN_volume());
        BigDecimal recommend_P_needed = new BigDecimal(recommendfertilizerbean.getP_volume());
        BigDecimal recommend_K_needed = new BigDecimal(recommendfertilizerbean.getK_volume());

        //专家推荐*用户亩数
        BigDecimal N_needed = userfildacres.multiply(recommend_N_needed);
        BigDecimal P_needed = userfildacres.multiply(recommend_P_needed);
        BigDecimal K_needed = userfildacres.multiply(recommend_K_needed);

        //被推荐的化肥的化肥含量
        BigDecimal fertilizer_N_percent = new BigDecimal(solve.getFertilizer_n());
        BigDecimal fertilizer_P_percent = new BigDecimal(solve.getFertilizer_p());
        BigDecimal fertilizer_K_percent = new BigDecimal(solve.getFertilizer_k());

        //此处出错了：
        BigDecimal final_recommendN = BigDecimal.ZERO;
        BigDecimal final_recommendP = BigDecimal.ZERO;
        BigDecimal final_recommendK = BigDecimal.ZERO;
        if (fertilizer_N_percent.compareTo(BigDecimal.ZERO) != 0) {
            final_recommendN = N_needed.divide(fertilizer_N_percent, 2);
        }
        if (fertilizer_P_percent.compareTo(BigDecimal.ZERO) != 0) {
            final_recommendP = P_needed.divide(fertilizer_P_percent, 2);
        }
        if (fertilizer_K_percent.compareTo(BigDecimal.ZERO) != 0) {
            final_recommendK = K_needed.divide(fertilizer_K_percent, 2);
        }
        BigDecimal array[] = {final_recommendN, final_recommendP, final_recommendK};
        BigDecimal max = array[0];
        //获取最大值
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(max) == 1) {
                max = array[i];
            }
        }
        //需要的价格计算
        BigDecimal price_kg = new BigDecimal(solve.getFertilizer_price());
        BigDecimal final_price = price_kg.multiply(max).setScale(2, BigDecimal.ROUND_HALF_UP);//保留两位小数

        fertilizerneedMap.put("kg_need", max.toString());
        fertilizerneedMap.put("money_need", final_price.toString());
        fertilizerneedMap.put("fertilizer_needbean", solve);
        return fertilizerneedMap;
    }

    static Fertilizer solve(List<Fertilizer> fertilizers, recommendElements recommend) {
        Fertilizer result = null;
        BigDecimal minDistance = new BigDecimal("99999");
        System.out.println("距离:");
        Map<Object, String> sortNPKmap = new HashMap<>();

        for (Fertilizer fertilizer : fertilizers) {
            // 欧式距离
            BigDecimal XN = new BigDecimal(Float.parseFloat(fertilizer.getFertilizer_n().toString()) / 100);
            BigDecimal XP = new BigDecimal(Float.parseFloat(fertilizer.getFertilizer_p().toString()) / 100);
            BigDecimal XK = new BigDecimal(Float.parseFloat(fertilizer.getFertilizer_n().toString()) / 100);
            BigDecimal XPrice = new BigDecimal(fertilizer.getFertilizer_price() / 100);

            BigDecimal YN = new BigDecimal(recommend.getN_volume());
            BigDecimal YP = new BigDecimal(recommend.getP_volume());
            BigDecimal YK = new BigDecimal(recommend.getK_volume());
            BigDecimal YPrice = new BigDecimal("0");

            BigDecimal distance = new BigDecimal(Math.sqrt(Math.pow(Math.abs(XN.subtract(YN).doubleValue()), 2) +
                    Math.pow(Math.abs(XP.subtract(YP).doubleValue()), 2) +
                    Math.pow(Math.abs(XK.subtract(YK).doubleValue()), 2) +
                    Math.pow(Math.abs(XPrice.subtract(YPrice).doubleValue()), 2)));
            System.out.println("id: " + fertilizer.getFertilizer_id() + "  distance: " + String.format("%.2f", distance));

            //将计算的距离放到sortNPKmap里面
            sortNPKmap.put(fertilizer, distance.toString());

            //取出距离最近的fertilizer
            if (distance.compareTo(minDistance) < 0) {
                minDistance = distance;
                result = fertilizer;
            }

        }

        //排序取前三个点
        //Map sortedMap=sortMap(sortNPKmap);
        //System.out.println(sortedMap);
        //System.out.println(result);
        return result;
        //return sortedMap;
    }

    //排序，取前三个点
    private static Map sortMap(Map<Object, String> sortNPKmap) {
        //获取entrySet
        Set<Map.Entry<Object, String>> mapEntries = sortNPKmap.entrySet();

        //使用链表来对集合进行排序，使用LinkedList，利于插入元素
        List<Map.Entry<Object, String>> result = new LinkedList<>(mapEntries);
        //自定义比较器来比较链表中的元素
        Collections.sort(result, new Comparator<Map.Entry<Object, String>>() {
            //基于entry的值（Entry.getValue()），来排序链表
            @Override
            public int compare(Map.Entry<Object, String> o1, Map.Entry<Object, String> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //将排好序的存入到LinkedHashMap(可保持顺序)中，需要存储键和值信息对到新的映射中。
        Integer sort = 1;
        Map<Object, String> linkMap = new LinkedHashMap<>();
        for (Map.Entry<Object, String> newEntry : result) {

            // 取出排名前3的值
            if (sort <= 3) {
                linkMap.put(newEntry.getKey(), newEntry.getValue());
                ++sort;
            }


        }
        return linkMap;


    }


}
