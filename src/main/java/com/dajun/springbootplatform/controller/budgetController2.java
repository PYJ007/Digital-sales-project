package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.repository.seedRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@RestController
public class budgetController2 {
    @Resource
    com.dajun.springbootplatform.repository.seedRepository seedRepository;

    @PostMapping("/budget")
    public void getValue(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int a = Integer.parseInt(request.getParameter("fertilizer"));
        int b = Integer.parseInt(request.getParameter("waterElectricity"));
        int c = Integer.parseInt(request.getParameter("artifical"));
        int d = Integer.parseInt(request.getParameter("seedFee"));
        double price = seedRepository.getPrice(request.getParameter("predict"));
        double numb = d/price;
//        暂时默认每个种子收益10元;
        double total = numb*10-a-b-c;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(String.format("%.2f",total));
    }
}
