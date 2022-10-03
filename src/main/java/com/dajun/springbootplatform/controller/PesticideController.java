package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Pesticide;
import com.dajun.springbootplatform.repository.PesticideMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class PesticideController {
    @Resource
    private PesticideMapper pesticideMapper;

    @RequestMapping("/recommendNY")
    public String getRecommendNY(Model model){
        List<Pesticide> pesticides = pesticideMapper.selectnongyaolistbynull();
        model.addAttribute("pesticides",pesticides);
        return "recommend/recommendNY";
    }

    @PostMapping("/deletePesticide")
    public String deletePesticide(@RequestParam Integer id){
        pesticideMapper.deletonepesticide(id);
        return "redirect:/recommendNY";
    }

    //专家插入农药信息
    @PostMapping("/pesticideinfoinsert")
    public String nongyaoinsert(Pesticide pesticide){
        pesticideMapper.insertpesticide(pesticide);
        int id = pesticide.getPesticide_id();
        if (id < 21000){
            pesticideMapper.updatePesticideId(id,"21000"+id);
        }
        return "redirect:/recommendNY";
    }
}
