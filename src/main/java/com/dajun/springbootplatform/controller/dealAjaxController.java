package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.repository.seedRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class dealAjaxController {
    @Resource
    seedRepository seedRepository;

    @PostMapping("/showPhoto")
    public void showPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String seedname = request.getParameter("cropname");
//        String photoName=seedRepository.findPhotoBySeedName(seedname);
        String photoName=String.valueOf(seedRepository.findSeedIdByName(seedname));
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(photoName);
    }

}
