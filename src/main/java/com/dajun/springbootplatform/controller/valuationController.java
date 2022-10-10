package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.valuate.candidateSeed;
import com.dajun.springbootplatform.entities.valuate.valuation;
import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.repository.specialistRepository;
import com.dajun.springbootplatform.repository.valuationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping("/valuation")
@Controller
public class valuationController {
    @Resource
    private specialistRepository specialistRepository;
    @Resource
    private seedRepository seedRepository;

    @Resource
    private valuationRepository valuationRepository;

    @PostMapping("/search")
    public String search(Model model, HttpServletRequest request){
        String userName = request.getParameter("search");
        List<candidateSeed> seeds = seedRepository.getCandidateByManufacture(userName);
        model.addAttribute("seeds",seeds);
        return "valuatorIndex";
    }

    @GetMapping("/search2")
    public String search2(Model model, HttpServletRequest request){
        String userName = request.getParameter("search");
        List<candidateSeed> seeds = seedRepository.getCandidateByManufacture(userName);
        model.addAttribute("seeds",seeds);
        return "valuatorIndex";
    }

    @PostMapping("/searchReject")
    public String searchReject(Model model, HttpServletRequest request){
        String userName = request.getParameter("search");
        List<candidateSeed> seeds = seedRepository.getRejectCandidateByManufacture(userName);
        model.addAttribute("seeds",seeds);
        return "rejectValuation";
    }

    @GetMapping("/reject")
    public String reject(Model model){
        List<candidateSeed> seeds = seedRepository.getCandidate4();
        model.addAttribute("seeds",seeds);
        return "rejectValuation";
    }

    @PostMapping("/saveValuation")
    public String saveValuation(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file, HttpSession session){//@RequestParam("file") MultipartFile file
        String hid=request.getParameter("hid");
        Integer seed_id=Integer.valueOf(request.getParameter("seed_id"));
        Integer purity=Integer.valueOf(request.getParameter("purity"));
        Integer neatness=Integer.valueOf(request.getParameter("neatness"));
        Integer germinationrate=Integer.valueOf(request.getParameter("germinationrate"));
        Integer wet=Integer.valueOf(request.getParameter("wet"));
        String rank=request.getParameter("rank"+hid);
        String grade=request.getParameter("grade");
        String phone = String.valueOf(session.getAttribute("userPhone"));
        int specialistId = specialistRepository.findIdByPhone(phone);
        valuation valuation = new valuation(seed_id,purity,neatness,germinationrate,wet,rank,grade,specialistId);
        valuationRepository.saveValuation(valuation);
        InputStream is=null;
        try {
            is = file.getInputStream();
            byte[] pic = new byte[(int)file.getSize()];
            is.read(pic);
            seedRepository.saveCandidateFileById(pic,seed_id);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

//        System.out.println("hid="+hid+" seed_id="+seed_id+"  purity="+purity+"  neatness="+neatness+"   qermmination="+germinationrate+"    wet="+wet+" rank="+rank+"   grade="+grade);
        List<candidateSeed> seeds = seedRepository.getCandidateByManufacture(null);
        model.addAttribute("seeds",seeds);
        return "valuatorIndex";
    }
}
