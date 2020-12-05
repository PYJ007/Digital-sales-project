package com.dajun.springbootplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class recommendController {

    @RequestMapping("/reco")
    public String recommend(){
        return "recommend/recommend";
    }
}
