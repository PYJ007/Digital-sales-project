package com.dajun.springbootplatform.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converse {

    private static List<String> sowMethod = Arrays.asList("插秧","保墒旱直播","播后上水","催芽撒播");
    private static List<String> Operations = Arrays.asList("施水操作","施肥操作","施药操作","其他操作");

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

    public String cropNameConverse(int x){ //这个返回的是作物类型
        List<String> cropList = Arrays.asList("玉米","水稻","枸杞");

        return cropList.get(x);
    };

    public String cropTypeConverse(int x,int y){  //这个返回的是具体的作物名称
        List<String> corn = Arrays.asList("保玉1号","苏玉31","中糯1号");
        List<String> rice = Arrays.asList("旱优73","宁粳4号","宁粳1号");
        List<String> gou_qi = Arrays.asList("中华枸杞","北方枸杞","宁夏枸杞");

        List<List<String>> crops = new ArrayList<>();
        crops.add(corn);
        crops.add(rice);
        crops.add(gou_qi);
        return crops.get(x).get(y);
    };

    //返回种植操作类型名称
    public String sowMethodConverse(int x){
        return sowMethod.get(x);
    }

    //根据种植操作名称返回操作数
    public int sowMethodReverse(String sow){
        for(int i=0;i<sowMethod.size();i++){
            if (sow.equals(sowMethod.get(i))) return i;
        }
        return 0;
    }

    //根据数字返回具体操作名称，施肥，施水什么的
    public String operationConverse(int x){
        return Operations.get(x-1);
    }
}
