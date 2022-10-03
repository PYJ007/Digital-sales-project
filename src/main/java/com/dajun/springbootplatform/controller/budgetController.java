package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.repository.seedRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class budgetController {
    @Resource
    seedRepository seedRepository;
    @GetMapping("/jump")
    public String toBudgetHtml(Model model){
        List<Seed> seeds = seedRepository.findAllSeed();
        model.addAttribute("seeds",seeds);
        return "budget";
    }
}
