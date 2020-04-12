package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.repository.userRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    public String UserLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session){
        if (password.isEmpty()||username.isEmpty()){
            session.setAttribute("errorMsg","账号或密码不可以为空");
            return "loginOrRegister/loginTest";
        }
        if (userRepository.findByAccount(username)!=null && userRepository.findByAccount(username).getUser_pass().equals(password)){
            //session过期时间设置为7200秒 即两小时  -> 我设置成了一周
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            session.setAttribute("userName",username);
            session.setAttribute("userMsg",3);
            return "redirect:/index";
        }else {
            session.setAttribute("errorMsg","账号或密码错误");
            return "loginOrRegister/loginTest";
        }
    }
}

