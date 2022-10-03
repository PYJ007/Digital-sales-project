package com.dajun.springbootplatform.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createSeedNo {
    public String create(String type){
        Date d = new Date();
        String code = "宁审";
        char typeIndex = type.charAt(type.length()-1);
        if (typeIndex=='米') typeIndex='玉';
        else if (typeIndex=='萄') typeIndex='葡';
        code+=typeIndex;
        DateFormat sdf3 = new SimpleDateFormat("YYYYmmss");
        code+=sdf3.format(d);
        return code;

    }
}
