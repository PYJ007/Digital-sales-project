package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.valuate.candidateSeed;
import com.dajun.springbootplatform.entities.valuate.valuation;
import com.dajun.springbootplatform.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminSeed {

    @Resource
    private AdminSeedRepository adminSeedRepository;

    @Resource
    private seedRepository seedRepository;

    @Resource
    private specialistRepository specialistRepository;

    @Resource
    private valuationRepository valuationRepository;

//    来到种子总览页面，也就是农资主页面
    @GetMapping("/res")
    public String resIndex(Model model){
        List<candidateSeed> seeds = seedRepository.getCandidate3();
        model.addAttribute("seeds",seeds);
        return "admin/adminRes";
    }

    @GetMapping("/seedDetails")
    public String seedDetails(Model model,
                              @RequestParam("seed_id") Integer seed_id){
        candidateSeed seed = seedRepository.fondCSeedById(seed_id);
        model.addAttribute("seed",seed);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("ft",ft);
        valuation valuation = valuationRepository.findValuationById(seed_id);
        model.addAttribute("valuation",valuation);
//        String specialistName = specialistRepository.findNameById(seed.getSpecialist_id());
//        model.addAttribute("sName",specialistName);
        return "admin/seedInfo";
    }

    @GetMapping("/seedAuthentication")
    public String seedAuthentication(Model model,
                              @RequestParam("seed_id") Integer seed_id){
        candidateSeed seed = seedRepository.fondCSeedById(seed_id);
        model.addAttribute("seed",seed);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("ft",ft);
//        String specialistName = specialistRepository.findNameById(seed.getSpecialist_id());
//        model.addAttribute("sName",specialistName);
        return "seedAuthentication";
    }

    @GetMapping("/passSeed")
    public String passSeed(@RequestParam("seed_id") Integer seed_id){
        candidateSeed candidateSeed = seedRepository.fondCSeedById(seed_id);
        seedRepository.deleteCSeed(seed_id);
        seedRepository.changeCSeedToSeed(candidateSeed);
        return "redirect:/admin/res";
    }

    @PostMapping("/rejection")
    public String rejection(ServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        String reason = request.getParameter("reason");
        seedRepository.updateCSeed(id,reason);
        return "redirect:/admin/res";

    }
}
