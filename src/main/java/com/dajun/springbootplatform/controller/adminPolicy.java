package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.other.policyAndLink;
import com.dajun.springbootplatform.repository.policyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminPolicy {

    @Resource
    private policyRepository policyRepository;
    @Resource
    private com.dajun.springbootplatform.repository.specialistRepository specialistRepository;

    //相应进入管理界面
    @GetMapping("/policy")
    public String register(Model model){
        List<policyAndLink> policyAndLinks = policyRepository.findAllNotPass();
        model.addAttribute(specialistRepository);
        model.addAttribute(policyAndLinks);
        return "admin/adminPolicy";
    }

    //具体的政策页面
    @GetMapping("/policyDetails")
    public String recommendDetails(@RequestParam("policy_id") Integer policy_id,
                                  Model model){
        policyAndLink policyAndLink = policyRepository.findPolicyAndLink(policy_id);
        model.addAttribute(specialistRepository);
        model.addAttribute(policyAndLink);
        return "admin/policyInfo";
    }

    //处理过审
    @GetMapping("/policyPass")
    public String recommendDetails(@RequestParam("policy_id") Integer policy_id){
        String id = policy_id.toString().split("00")[1];
        policyRepository.updateLink(policy_id,id);
        policyRepository.updatePolicyId(policy_id,id);
        return "redirect:/admin/policy";
    }
}
