package com.dajun.springbootplatform.controller;


import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.repository.FertilizerMapper;
import com.dajun.springbootplatform.repository.adminFertilizerRepository;
import com.dajun.springbootplatform.service.FertilizerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FertilizerController {

    @Autowired
    FertilizerServiceImpl fertilizerService;

    @Resource
    FertilizerMapper fertilizerMapper;

    @Resource
    private com.dajun.springbootplatform.repository.adminFertilizerRepository adminFertilizerRepository;

    @GetMapping("/recommendNZ")
    public String recommendNZ(Model model){
        return "recommend/recommendNZ";
    }

    @GetMapping("/recommendHF")
    public String recommendHF(Model model){
        List<Fertilizer> fertilizers = fertilizerService.selectfertilizerList();
        model.addAttribute(fertilizers);
        return "recommend/recommendHF";
    }

    //删除化肥信息
    @PostMapping(value = "/specialistForm1")
    public String deleteFertilizer(@RequestParam Integer recommend_id){
        fertilizerService.deleterFertilizerById(recommend_id);
        return "redirect:/recommendHF";
    }

    //专家插入化肥信息
    @PostMapping("/fertilizerinfoinsert")
    public String huafeiinsert(@RequestParam String fertilizerName,
                                     @RequestParam String fertilizerType,
                                     @RequestParam String fertilizerN,
                                     @RequestParam String fertilizerP,
                                     @RequestParam String fertilizerK,
                                     @RequestParam String fertilizerOther,
                                     @RequestParam String fertilizerInstructions,
                                     @RequestParam String fertilizerPrice,
                                     @RequestParam String fertilizerManufacturer,
                                     @RequestParam String fertilizerPhone,
                                     @RequestParam String fertilizerAddress,
                                     @RequestParam String fertilizerProductiondate,
                                     @RequestParam String fertilizerShelflife,
                                     @RequestParam String fertilizerModeratecrop,
                                     @RequestParam String recommendData
    ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setFertilizer_address(fertilizerAddress);
        fertilizer.setFertilizer_instructions(fertilizerInstructions);
        fertilizer.setFertilizer_name(fertilizerName);
        fertilizer.setFertilizer_type(Integer.parseInt(fertilizerType));
        fertilizer.setFertilizer_n(fertilizerN);
        fertilizer.setFertilizer_p(fertilizerP);
        fertilizer.setFertilizer_k(fertilizerK);
        fertilizer.setFertilizer_other(fertilizerOther);
        fertilizer.setFertilizer_price(Double.parseDouble(fertilizerPrice));
        fertilizer.setFertilizer_manufacturer(fertilizerManufacturer);
        fertilizer.setFertilizer_phone(fertilizerPhone);
        fertilizer.setFertilizer_productiondate(simpleDateFormat.parse(fertilizerProductiondate));
        fertilizer.setFertilizer_manufacturer(fertilizerManufacturer);
        fertilizer.setFertilizer_shelflife(Integer.parseInt(fertilizerShelflife));
        fertilizer.setFertilizer_moderatecrop(fertilizerModeratecrop);
        fertilizer.setRecommend_data(simpleDateFormat.parse(recommendData));
        fertilizerMapper.insertfertilizer(fertilizer);
        int id = fertilizer.getFertilizer_id();
        if (id<21000){
            adminFertilizerRepository.updateFertilizerId(id,"21000"+id);
        }
        return  "redirect:/recommendHF";
    }


}

