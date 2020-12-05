package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.repository.collectionProductRepository;
import com.dajun.springbootplatform.repository.fieldRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class
collectionController {

    @Resource
    private collectionProductRepository collectionProductRepository;

    @Resource
    private fieldRepository fieldRepository;

    @GetMapping("/collect")
    public String collect(Model model,
                          HttpSession session){

        String collect_list = "collection" ;
        Collection<Seed> seeds = collectionProductRepository.findSeed(collect_list);
        Collection<Fertilizer> fertilizers = collectionProductRepository.findFertilizer(collect_list);
//                collectDetailDao.getProducts();
        model.addAttribute("seeds",seeds);
        model.addAttribute("fertilizers",fertilizers);
        if (fieldRepository.showField(session.getAttribute("userPhone").toString()).getUser_card()==null)
            session.setAttribute("numbers",1);
        return "collection/collection";
    }
}
