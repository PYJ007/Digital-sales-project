package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.Specialist;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.entities.valuate.valuation;
import com.dajun.springbootplatform.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class HistoricalProcessController {
    @Resource
    private fieldRepository fieldRepository;
    @Resource
    private valuationRepository valuationRepository;

    @Resource
    private seedRepository seedRepository;

    @Resource
    private historicalProcessRepository historicalProcessRepository;
    @Resource
    private com.dajun.springbootplatform.repository.specialistRepository specialistRepository;

    @GetMapping("/history")
    private String history(@RequestParam("fieldId") String fieldId,@RequestParam("time") String time, Model model,HttpSession session){
        String groupId = fieldId+"-"+time;
        if(historicalProcessRepository.findHistoryOrnot(groupId)!=null) {
            String userPhone = String.valueOf(session.getAttribute("userPhone"));
            List<field> fields = fieldRepository.findFieldByTel(userPhone);
            model.addAttribute("fields", fields);
            List<recommend> recommends = historicalProcessRepository.findRecommendsByGroupId(groupId);
            for (int i = 0; i < recommends.size(); i++) {
                int id = recommends.get(i).getRecommend_id();
                Date date = historicalProcessRepository.findExcuteTime(id, groupId);
                recommends.get(i).setExcute_time(date);
            }
            int seed_id = historicalProcessRepository.findSeedIdByGroupId(groupId);
            Seed seed = seedRepository.findSeedById(seed_id);
            valuation valuation = valuationRepository.findValuationById(seed_id);
            int specialistId = valuationRepository.findValuationById(seed_id).getSpecialist_id();
            Specialist specialist = specialistRepository.findById(specialistId);
            model.addAttribute("specialist", specialist);
            model.addAttribute("recommends", recommends);
            model.addAttribute("seed", seed);
            model.addAttribute("valuation", valuation);
        }
        else {
            String userPhone = String.valueOf(session.getAttribute("userPhone"));
            List<field> fields = fieldRepository.findFieldByTel(userPhone);
            model.addAttribute("fields",fields);
        }
        return "importantRecorde";
    }

    @GetMapping("/firstIntoHistory")
    private String firstIntoHistory(Model model, HttpSession session){
        String userPhone = String.valueOf(session.getAttribute("userPhone"));
        List<field> fields = fieldRepository.findFieldByTel(userPhone);
        model.addAttribute("fields",fields);
        return "importantRecorde";
    }

}
