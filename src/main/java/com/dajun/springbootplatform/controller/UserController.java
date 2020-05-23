package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.repository.userRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private userRepository userRepository;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "loginOrRegister/loginTest";
    }

    @PostMapping(value = "/UserLogin")
    public String UserLogin(@RequestParam("phone") String phone,
                          @RequestParam("password") String password,
                          HttpSession session){
        String username = userRepository.findName(phone);
        if (password.isEmpty()||phone.isEmpty()){
            session.setAttribute("errorMsg","账号或密码不可以为空");
            return "loginOrRegister/loginTest";
        }
        if (userRepository.findByAccount(phone)!=null && userRepository.findByAccount(phone).getUser_pass().equals(password)){
            //session过期时间设置为7200秒 即两小时  -> 我设置成了一周
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            session.setAttribute("userName",username);
            session.setAttribute("userPhone",phone);
            session.setAttribute("userMsg",3);
            return "redirect:/index";
        }else {
            session.setAttribute("errorMsg","账号或密码错误");
            return "loginOrRegister/loginTest";
        }
    }

}

