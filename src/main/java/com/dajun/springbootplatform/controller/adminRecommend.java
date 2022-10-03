package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.application.Converse;
import com.dajun.springbootplatform.entities.admin;
import com.dajun.springbootplatform.entities.other.recommendInfo;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.repository.adminRecommendMapper;
import com.dajun.springbootplatform.repository.recommendJudgeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller

@RequestMapping("/admin")
public class adminRecommend {

    @Resource
    private adminRecommendMapper adminRecommendMapper;

    @Resource
    private recommendJudgeMapper recommendJudgeMapper;

    @Resource
    private com.dajun.springbootplatform.repository.seedRepository seedRepository;

    @Resource
    private com.dajun.springbootplatform.repository.specialistRepository specialistRepository;

    //相应进入管理界面
    @GetMapping("/login")
    public String login(){
        return "admin/adminLogin";
    }

    //判断是否进入
    @PostMapping("/adminRecommend")
    public String getRecommend(@RequestParam("phone") String phone,
                               @RequestParam("password") String password){
        admin admin =adminRecommendMapper.selectAdminByName(phone);
        if (admin.getAdmin_pass().equals(password)) return "redirect:/admin/adminIndex";
        else return "redirect:/admin/login";
    }
    //进入具体页面
    @GetMapping("/adminIndex")
    public String adminIndex(Model model){
        List<recommendInfo> recommendInfo = recommendJudgeMapper.findRecommendInfo();
        model.addAttribute(recommendInfo);
        model.addAttribute(specialistRepository);//专家名字转换
        model.addAttribute(seedRepository);//种子名称转化
        model.addAttribute("converse", new Converse());//种植类型转换
        return "admin/adminRecommend";
    }
    //具体的农时信息（根据种子、专家、种植方式查询具体信息）
    @GetMapping("/recommendDetails")
    public String recommendDetails(@RequestParam("specialist_id") Integer specialist_id,
                                   @RequestParam("seed_id") Integer seed_id,
                                   @RequestParam("sowmethod") Integer sowmethod,Model model){
        List<recommend> recommendInfo = recommendJudgeMapper.findRecByIDAndSow(specialist_id,seed_id,sowmethod);
        model.addAttribute(recommendInfo);
        model.addAttribute("cropName",seedRepository.findSeedNameById(seed_id));
        model.addAttribute("converse", new Converse());//种植类型转换
        model.addAttribute("sowMethod",new Converse().sowMethodConverse(sowmethod));
        return "admin/recommendJudgeInfo";
    }

    //处理过审
    @GetMapping("/recommendPass")
    public String recommendDetails(@RequestParam("specialist_id") Integer specialist_id,
                                   @RequestParam("seed_id") Integer seed_id,
                                   @RequestParam("sowmethod") Integer sowmethod){
        recommendJudgeMapper.passRecommend(specialist_id,seed_id,sowmethod);
        recommendJudgeMapper.insertToRecommend();
        return "redirect:/admin/adminIndex";
    }
}
