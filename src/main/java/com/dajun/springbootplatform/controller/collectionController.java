package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.Specialist;
import com.dajun.springbootplatform.entities.connect.answers;
import com.dajun.springbootplatform.entities.connect.questions;
import com.dajun.springbootplatform.repository.*;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class
collectionController {

    @Resource
    private collectionProductRepository collectionProductRepository;

    @Resource
    private fieldRepository fieldRepository;

    @Resource
    private specialistRepository specialistRepository;

    @Resource
    private questionRepository questionRepository;

    @Resource
    private answerRepository answerRepository;

    @Resource
    private userRepository userRepository;

    @GetMapping("/collect")
    public String collect(Model model,
                          HttpSession session){

        String collect_list = "collection" ;
        Collection<Seed> seeds = collectionProductRepository.findSeed(collect_list);
        Collection<Fertilizer> fertilizers = collectionProductRepository.findFertilizer(collect_list);
//                collectDetailDao.getProducts();
        List<questions> questions = questionRepository.findquestionByPhone(session.getAttribute("userPhone").toString());
        List<answers> answers = answerRepository.findAnswerByPhone(session.getAttribute("userPhone").toString());
        String specialist_tel=userRepository.findSpecialistTel(session.getAttribute("userPhone").toString());
        Specialist specialist = specialistRepository.findByAccount(specialist_tel);
        model.addAttribute("specialist",specialist);
        model.addAttribute("seeds",seeds);
        model.addAttribute("fertilizers",fertilizers);
        model.addAttribute("questions",questions);
        model.addAttribute("answers",answers);
        if (fieldRepository.showField(session.getAttribute("userPhone").toString()).getUser_card()==null)
            session.setAttribute("numbers",1);
        return "collection/collection";
    }

    @PostMapping("/question")
    public String question(HttpServletRequest request,HttpSession session){
        String question=request.getParameter("question");
        String userPhone= session.getAttribute("userPhone").toString();
        questionRepository.saveQuestion(question,0,userPhone);
        return "redirect:/collect";
    }

    @GetMapping("/deleteMessageq")
    public String deleteMessageg(Integer id){
        questionRepository.deleteQuestion(id);
        return "redirect:/collect";
    }

    @GetMapping("/deleteMessageA")
    public String deleteMessageA(Integer id){
        answerRepository.deleteAnswer(id);
        return "redirect:/collect";
    }

    @GetMapping("/setMessageA2")
    public String setMessageA2(Integer id){
        answerRepository.setState(id);
        return "redirect:/collect";
    }
}
