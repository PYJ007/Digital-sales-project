package com.dajun.springbootplatform.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class staticVariety {

    //图片存放路径
    public static final String imgPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\nongye_image";

    //文件存放路径
    public static final String filePath = "classpath:\\nongye_policyfile\\nongye_policyfile";

    //作物类型    写死,暂定8类且不变
    public static final List<String> cropsType = Arrays.asList("枸杞","葡萄","向日葵","水稻","玉米","小麦","大豆","马铃薯");

    //种植方式，这个应该是特指水稻的
    public static final List<String> sowMethod = Arrays.asList("插秧","保墒旱直播","播后上水","催芽撒播");

    //操作类型
    public static final List<String> Operations = Arrays.asList("施水操作","施肥操作","施药操作","其他操作");
}
