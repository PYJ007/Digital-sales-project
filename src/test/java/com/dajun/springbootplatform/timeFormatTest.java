package com.dajun.springbootplatform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeFormatTest {
    public static void main(String[] args) throws ParseException {
        String format = "2020-11-02";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(format);
        System.out.println(ft.parse(format));
    }
}
