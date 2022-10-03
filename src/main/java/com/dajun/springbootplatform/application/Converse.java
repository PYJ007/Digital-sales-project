package com.dajun.springbootplatform.application;

import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.repository.specialistRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converse {

    @Resource
    private seedRepository seedRepository;

    @Resource
    private specialistRepository specialistRepository;

        //这个城市肯定是不会变的(至少很长时间都不会变)
    public String cityConverse(int x,int y){
        List<String> provList = Arrays.asList("银川市","石嘴山市","吴忠市","固原市","中卫市");

        List<String> yin_chuan = Arrays.asList("西夏区","金凤区","兴庆区","永宁县","贺兰县","武灵市");
        List<String> shi_zui_shan = Arrays.asList("大武口","惠农区","平罗县");
        List<String> wu_zhong = Arrays.asList("利通区","红寺堡区","盐池县","同心县","青铜峡市");
        List<String> gu_yuan = Arrays.asList("原州区","西吉县","隆德县","泾源县","彭阳县");
        List<String> zhong_wei = Arrays.asList("沙坡头区","中宁县","海原县");

        List<List<String>> cityList = new ArrayList<>();
        cityList.add(yin_chuan);
        cityList.add(shi_zui_shan);
        cityList.add(wu_zhong);
        cityList.add(gu_yuan);
        cityList.add(zhong_wei);

        return provList.get(x) + " " + cityList.get(x).get(y) ;
    };

    //作物一定会变的，想办法从数据库读出来，不要在这里写死

    //返回种植操作类型名称
    public String sowMethodConverse(int x){
        return staticVariety.sowMethod.get(x);
    }

    //根据种植操作名称返回操作数
    public int sowMethodReverse(String sow){
        for(int i=0;i<staticVariety.sowMethod.size();i++){
            if (sow.equals(staticVariety.sowMethod.get(i))) return i;
        }
        return 0;
    }

    //根据数字返回具体操作名称，施肥，施水什么的
    public String operationConverse(int x){
        return staticVariety.Operations.get(x-1);
    }

    //根据种子ID返回种子名称
    public String findSeedName(int id){
        return seedRepository.findSeedNameById(id);
    }

    //根据专家ID获取专家名称
    //这个不知道为什么，一直空指针异常。
    public String findSpecialistName(int id){
        return specialistRepository.findNameById(id);
    }

}
