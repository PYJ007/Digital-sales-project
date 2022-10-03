package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.other.policyAndLink;
import com.dajun.springbootplatform.entities.policy;
import com.dajun.springbootplatform.entities.valuate.valuation;
import com.dajun.springbootplatform.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class recommendController {

    @Resource
    private recommendRepository recommendRepository;
    @Resource
    private specialistRepository specialistRepository;
    @Resource
    private policyRepository policyRepository;
    @Resource
    private seedRepository seedRepository;
    @Resource
    private userRepository userRepository;
    @Resource
    private valuationRepository valuationRepository;

    //去推荐页面
    @RequestMapping("/reco")
    public String recommend(Model model){
        //把政策和种子信息放进去
        //三个政策，三个种子
//        List<policyAndLink> policies = policyRepository.selectThreePolicy();
        List<Seed> seedList = seedRepository.findThreeSeed();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("ft",ft);
        model.addAttribute(seedList);
//        model.addAttribute("policies",policies);
        return "recommend/recommend";
    }

    //处理种子详情页面的信息
    @RequestMapping("/seedRecInfo/{id}")
    public String getSeedRecInfo(@PathVariable("id") Integer id ,
                                 Model model,
                                 HttpSession session){
        String userPhone = String.valueOf(session.getAttribute("userPhone"));
        userRepository.insertBrowsing(userPhone,id);
        model.addAttribute("seedId",id);
        model.addAttribute("userName",session.getAttribute("userName"));
        //找到相应的种子信息
        Seed seed = seedRepository.findSeedById(id);
        model.addAttribute(seed);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("ft",ft);
        //转化商家信息
        User seller = userRepository.findByAccount(seed.getSeed_phone());
        //String specialistName = specialistRepository.findNameById(seed.getSpecialist_id());
        model.addAttribute("seller",seller);
        valuation valuation = valuationRepository.findValuationById(id);
        model.addAttribute("valuation",valuation);
        return "recommend/seedRecInfo";
    }

    //处理政策详情页面
    @RequestMapping("/policyRecoInfo/{id}")
    public String getPolicyRecInfo(@PathVariable("id") Integer id,
                                   Model model){
        //找到相应的政策信息
        policy policy = policyRepository.findPolicyById(id);
        model.addAttribute(policy);
        //转化专家信息
        String specialistName = specialistRepository.findNameById(policy.getSpecialist_id());
        model.addAttribute("sName",specialistName);
        return "recommend/policyRecInfo";
    }


    @RequestMapping("/recommendPolicy")
    public String recommendPolicy(Model model){
        List<policy> policies = policyRepository.selectAllPolicy();
        model.addAttribute(policies);
        return "recommend/recommendPolicy";
    }

    @PostMapping(value = "deletePolicy")
    public String deletePolicy(@RequestParam("policy_id") Integer id){
        policyRepository.deletePolicyById(id);
        policyRepository.deletePolicyLLink(id);
        return "redirect:/recommendPolicy";
    }

    //政策提交
    @PostMapping(value = "/policyPost")
    public String insertSeedInfo(@RequestParam("link") String link,
                                 @RequestParam("policyTitle") String policyTitle,//政策标题
                                 @RequestParam("policyContent") String policyContent,//政策内容
                                 HttpSession session) {
        //获取userController放入的专家手机号
        String specialistPhone = session.getAttribute("userPhone").toString();
        int specialist_id = specialistRepository.findIdByPhone(specialistPhone);
        //获取userController放入的专家ID
        policy policy = new policy();
        policy.setPolicy_content(policyContent);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        policy.setPolicy_time(df.format(new Date()));
        policy.setPolicy_title(policyTitle);
        policy.setSpecialist_id(specialist_id);
        recommendRepository.savePolicy(policy);
        String newId = "21000" + policy.getPolicy_id();
        String id = newId.split("00")[1];
        //link必须包含协议，不然跳转不了啊
        String head = link.split("://")[0];
        if (!(head.equals("http")||head.equals("https"))){
            link="http://"+link;
        }
        if (policy.getPolicy_id()<21000){
            recommendRepository.insertPolicyLink(newId,link);
            recommendRepository.updatePolicyId(newId,policy.getPolicy_id());
        }else {
            recommendRepository.insertPolicyLink(""+policy.getPolicy_id(),link);
        }
        return "redirect:/recommendPolicy";
    }
}
