package com.dajun.springbootplatform.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class addShopCar {
    @Resource
    com.dajun.springbootplatform.repository.seedRepository seedRepository;
    @PostMapping("/addShopCar")
    public void addShopCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String seedId = request.getParameter("seedId");
        String userName = request.getParameter("userName");
        seedRepository.addShopCar(userName,Integer.parseInt(seedId));
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("1");
    }
}
