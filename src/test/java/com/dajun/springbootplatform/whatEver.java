package com.dajun.springbootplatform;

import com.dajun.springbootplatform.application.createSeedNo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class whatEver {
    public static void main(String[] args) {
        String a = "200001";
        String[] b = a.split("0");
        System.out.println(b[b.length-1]);
//        Date d = new Date();
//        String s = null;
//        DateFormat sdf3 = new SimpleDateFormat("yyyymmss");
//        s = sdf3.format(d);
//        System.out.println(s);
//
//        createSeedNo createSeedNo = new createSeedNo();
//        System.out.println(createSeedNo.create("水稻"));
    }
}
