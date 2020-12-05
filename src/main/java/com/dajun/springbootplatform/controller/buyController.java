package com.dajun.springbootplatform.controller;


import com.dajun.springbootplatform.entities.buy;
import com.dajun.springbootplatform.entities.other.test1List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class buyController {

    @Autowired
    com.dajun.springbootplatform.repository.buyRepository buyRepository;

    @GetMapping("/buy")
    public String buy(HttpSession session){
        buy b=new buy();
        b=buyRepository.findbuy();
        System.out.println(b);
        session.setAttribute("buy",b);
        return "buyList";
    }

    @GetMapping("/testbuy")
    public String testbuy(HttpSession session){
        String a="apple";
        int b=10;
//        String a[3];
        List<test1List> test1Lists = new ArrayList<>();
        test1List test1List2 = new test1List();
        test1List2.setName("banana");
        test1List2.setNum(1);
        test1List2.setValue(2);
        test1List test1List3 = new test1List();
        test1List3.setName("apple");
        test1List3.setNum(3);
        test1List3.setValue(4);
        test1Lists.add(test1List2);
        test1Lists.add(test1List3);
        session.setAttribute("tLists",test1Lists);
//        List<String> test1List = new ArrayList<>();
//        test1List.add("dajun");
//        test1List.add("jinlong");
//        test1List.add("daidiwei");
//        test1List[1];
//        session.setAttribute("test1list",test1List);
        session.setAttribute("test1num",b);
        session.setAttribute("banana",a);
        return "testHTML/test1";
    }


}
