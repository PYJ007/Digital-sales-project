package com.dajun.springbootplatform.controller;

//import com.dajun.springbootplatform.Dao.CollectDetailDao;
import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.Product;
import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.repository.collectionProductRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class
collectionController {

//    @Autowired
//    CollectDetailDao collectDetailDao;
//    @Resource
//    CollectDetailDao collectDetailDao;

    @Resource
    private collectionProductRepository collectionProductRepository;

    @GetMapping("/collect")
    public String collect(Model model){

        String collect_list = "collection" ;
        Collection<Seed> seeds = collectionProductRepository.findSeed(collect_list);
        Collection<Fertilizer> fertilizers = collectionProductRepository.findFertilizer(collect_list);
//                collectDetailDao.getProducts();
        model.addAttribute("seeds",seeds);
        model.addAttribute("fertilizers",fertilizers);
        return "collection/collection";
    }
}
