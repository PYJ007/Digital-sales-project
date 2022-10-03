package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Specialist;
import com.dajun.springbootplatform.entities.other.specialistInfo;
import com.dajun.springbootplatform.repository.certificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;

@Controller

@RequestMapping("/admin")
public class adminRegister {

    @Resource
    private certificationRepository certificationRepository;

    //相应进入管理界面
    @GetMapping("/register")
    public String register(Model model){
        List<Specialist> specialistInfos = certificationRepository.findAllNotPassSpecialistAndPic();
        model.addAttribute(specialistInfos);
        return "admin/adminRegister";
    }

    //具体的注册页面
    @GetMapping("/specialistDetails")
    public String recommendDetails(@RequestParam("specialist_id") Integer specialist_id,
                                  Model model){
        specialistInfo specialistInfo = certificationRepository.findNotPassSpecialist(specialist_id);
        specialistInfo.setSpecialist_phone(specialistInfo.getSpecialist_phone().split("_")[0]);
        model.addAttribute(specialistInfo);
        model.addAttribute("pic",Base64.getEncoder().encodeToString(specialistInfo.getPic()));
        return "admin/recommendRegisterInfo";
    }

    //处理过审
    @GetMapping("/specialistPass")
    public String recommendDetails(@RequestParam("specialist_id") Integer specialist_id,
                                   @RequestParam("phone") String phone){
        certificationRepository.passSpecialist(phone.split("_")[0],specialist_id);
        return "redirect:/admin/register";
    }
}
