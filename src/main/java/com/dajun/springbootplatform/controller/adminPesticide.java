package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Pesticide;
import com.dajun.springbootplatform.repository.PesticideMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminPesticide {

    @Resource
    private PesticideMapper pesticideMapper;


//    来到种子总览页面，也就是农资主页面
    @GetMapping("/pesticide")
    public String fertilizerIndex(Model model){
        List<Pesticide> pesticides = pesticideMapper.selectAllNotPassPesticide();
        model.addAttribute(pesticides);
        return "admin/adminPesticide";
    }

    @GetMapping("/pesticideDetails")
    public String seedDetails(Model model,
                              @RequestParam("pesticide_id") Integer pesticide_id){
        Pesticide pesticide = pesticideMapper.selectPesticideById(pesticide_id);
        model.addAttribute(pesticide);
        return "admin/pesticideInfo";
    }

    @GetMapping("/passPesticide")
    public String passSeed(@RequestParam("pesticide_id") Integer pesticide_id){
        String newId = pesticide_id.toString().split("00")[1];
        pesticideMapper.updatePesticideId(pesticide_id,newId);
        return "redirect:/admin/pesticide";
    }
}
