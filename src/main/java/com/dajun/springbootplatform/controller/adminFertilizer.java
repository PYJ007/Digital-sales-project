package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminFertilizer {

    @Resource
    private adminFertilizerRepository adminFertilizerRepository;


//    来到种子总览页面，也就是农资主页面
    @GetMapping("/fertilizer")
    public String fertilizerIndex(Model model){
        List<Fertilizer> fertilizers = adminFertilizerRepository.selectAllNotPassFertilizer();
        model.addAttribute(fertilizers);
        return "admin/adminFertilizer";
    }

    @GetMapping("/fertilizerDetails")
    public String seedDetails(Model model,
                              @RequestParam("fertilizer_id") Integer fertilizer_id){
        Fertilizer fertilizers = adminFertilizerRepository.selectFertilizerById(fertilizer_id);
        model.addAttribute(fertilizers);
        return "admin/fertilizerInfo";
    }

    @GetMapping("/passFertilizer")
    public String passSeed(@RequestParam("fertilizer_id") Integer fertilizer_id){
        String newId = fertilizer_id.toString().split("00")[1];
        adminFertilizerRepository.updateFertilizerId(fertilizer_id,newId);
        return "redirect:/admin/fertilizer";
    }
}
