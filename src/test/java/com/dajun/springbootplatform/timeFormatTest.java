package com.dajun.springbootplatform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeFormatTest {
    public static void main(String[] args) throws ParseException {
        String fileName = "123.rar";
        String format = "2020-11-02";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(format);
        System.out.println(ft.parse(format));
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);//获取后缀名
        System.out.println(suffix);
    }
}
