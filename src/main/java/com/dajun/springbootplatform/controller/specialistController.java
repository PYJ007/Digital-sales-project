package com.dajun.springbootplatform.controller;

import com.aliyuncs.ram.model.v20150501.ListVirtualMFADevicesResponse;
import com.dajun.springbootplatform.application.Converse;
import com.dajun.springbootplatform.entities.*;
import com.dajun.springbootplatform.entities.connect.answers;
import com.dajun.springbootplatform.entities.connect.questions;
import com.dajun.springbootplatform.entities.other.specialistAndSeedId;
import com.dajun.springbootplatform.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class specialistController {

//    营养元素顺序
    private static List<String> elements = Arrays.asList("钾","氮","磷");

    @Resource
    private userRepository userRepository;

    @Resource
    private seedRepository seedRepository;

    @Resource
    private recommendRepository recommendRepository;

    @Resource
    private specialistRepository specialistRepository;

    @Resource
    private com.dajun.springbootplatform.repository.questionRepository questionRepository;

    @Resource
    private com.dajun.springbootplatform.repository.answerRepository answerRepository;

    @GetMapping("/specialistIndex")
    public String getSpecialistIndex(){
        return "specialistIndex";
    }

    //进入推荐之前选择推荐作物名称的界面
    @GetMapping("/recommendFirst")
    public String getRecommendFirst(Model model){
        //为添加作物信息提供接口
        //枸杞
        model.addAttribute("wolfBerry",seedRepository.findDetailNameByTypeName("枸杞"));
        //水稻
        model.addAttribute("paddyRice",seedRepository.findDetailNameByTypeName("水稻"));
        //小麦
        model.addAttribute("wheat",seedRepository.findDetailNameByTypeName("小麦"));
        //大豆
        model.addAttribute("soybean",seedRepository.findDetailNameByTypeName("大豆"));
        //葡萄
        model.addAttribute("purple",seedRepository.findDetailNameByTypeName("葡萄"));
        //向日葵
        model.addAttribute("sunflower",seedRepository.findDetailNameByTypeName("向日葵"));
        //玉米
        model.addAttribute("corn",seedRepository.findDetailNameByTypeName("玉米"));
        //马铃薯
        model.addAttribute("potato",seedRepository.findDetailNameByTypeName("马铃薯"));
        return "recommendFirst";
    }

//  处理删除请求
    @PostMapping(value = "/specialistForm")
    public String getSpecialistForm(
            @RequestParam("operation") String operation,
            @RequestParam("recommend_id") Integer recommend_id,
            Model model){
            recommendRepository.deleteByRecommendId(recommend_id);
            recommendRepository.deleteJudgeById(recommend_id);
            return "redirect:/specialistReco";
    }

    //把种子信息加入session中
    @GetMapping("/specialistSeedName")
    public String specialistSeedName(@RequestParam("cropsName") String cropsName,
                                     HttpSession session){
        session.setAttribute("crops_name",cropsName);
        return "redirect:/specialistReco";
    }

//    专家推送信息的页面处理，要将数据库中的信息提出展示，要将专家ID放入SESSION中
    @GetMapping("/specialistReco")
    public String getSpecialistReco(
            HttpSession session,
            Model model
    ){
        String crops_name = session.getAttribute("crops_name").toString();
        int seed_id = seedRepository.findSeedIdByName(crops_name);
        session.setAttribute("seed_id",seed_id);
        int specialist_id = Integer.parseInt(session.getAttribute("specialist_id").toString());
        specialistAndSeedId specialistAndSeedId = new specialistAndSeedId();
        specialistAndSeedId.setSeed_id(seed_id);
        specialistAndSeedId.setSpecialist_id(specialist_id);
        List<recommend> recommends =  recommendRepository.findBySeedIdAndSpecialistId(specialistAndSeedId);
//        model.addAttribute("crops_name",crops_name);
        model.addAttribute("recommends",recommends);//遍历出来的对应的推荐信息
        model.addAttribute("converse", new Converse());
        return "specialistReco";
    }

//    处理专家推送时提交的水表,     后来我发现这个也可以提交其他
    @PostMapping(value = "/waterPost")
    public String waterPost(@RequestParam("sowMethod") String sowMethod,
                            @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime,
                            @RequestParam("stage") String stage,
                            @RequestParam("detail") String detail,
                            @RequestParam("notice") String notice,
                            @RequestParam("operation") String operation,
                            HttpSession session) {
        recommend recommend = new recommend();
        recommend.setDetail(detail);
        recommend.setNotice(notice);
        recommend.setRecommend_endtime(endTime);
        recommend.setRecommend_time(startTime);
        recommend.setSowmethod(new Converse().sowMethodReverse(sowMethod));
        recommend.setStage(stage);
        recommend.setRecommend_type(Integer.parseInt(operation));//水是1,其他是四
        recommend.setSeed_id(Integer.parseInt(session.getAttribute("seed_id").toString()));
        recommend.setSpecialist_id(Integer.parseInt(session.getAttribute("specialist_id").toString()));
        recommendRepository.insertJudgeBySeedId(recommend);
        session.setAttribute("sowMethod",sowMethod);
        return "redirect:/specialistReco";
    }


//  这里是提交化肥的处理，注意化肥元素的提交方式，需要关联上方的elements列表
    @PostMapping(value = "/fertilizerPost")
    public String getFertilizerPost(
            @RequestParam("sowMethod") String sowMethod,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("stage") String stage,
            @RequestParam("detail") String detail,
            @RequestParam("ingredient") List<String> ingredient,
            @RequestParam("ingredient-type") List<String> ingredient_type,
            @RequestParam("ingredient-volume") List<String> ingredient_volume,
            HttpSession session) {
        recommend recommend = new recommend();
        recommendElements recommendElements = new recommendElements();
        int specialistId = Integer.parseInt(session.getAttribute("specialist_id").toString());
        int seed_id = Integer.parseInt(session.getAttribute("seed_id").toString());
        recommend.setDetail(detail);
        recommend.setNotice("default"); //农药表没有注意事项
        recommend.setRecommend_endtime(endTime);
        recommend.setRecommend_time(startTime);
//        recommend.setRecommend_id();这个是自增的，不需要设置
        recommend.setSowmethod(new Converse().sowMethodReverse(sowMethod));
        recommend.setStage(stage);
        recommend.setRecommend_type(2);//肥料是2
        recommend.setSeed_id(seed_id);
        recommend.setSpecialist_id(specialistId);
        recommendRepository.insertJudgeBySeedId(recommend);
        for (int i=0;i<ingredient_volume.size();i++){//把没有填写值的列表更改成0
            if (ingredient_volume.get(i).equals("")||ingredient_volume.get(i).equals(" "))
                ingredient_volume.set(i,"0");
        }
        //字符转换int
        double k_volume = Integer.parseInt(ingredient_volume.get(0));
        double n_volume = Integer.parseInt(ingredient_volume.get(1));
        double p_volume = Integer.parseInt(ingredient_volume.get(2));
        recommendElements.setK_volume(k_volume);
        recommendElements.setN_volume(n_volume);
        recommendElements.setP_volume(p_volume);
        recommendElements.setRecommend_id(recommend.getRecommend_id());
        //通过专家ID查询到最新的推荐ID
        recommendElements.setRecommend_id(recommendRepository.findRecommendIdBySpecialistId(specialistId));
        recommendRepository.saveElements(recommendElements);
        session.setAttribute("sowMethod",sowMethod);
        return "redirect:/specialistReco";
    }

    //农药信息提交
    @PostMapping(value = "/pesticidePost")
    public String pesticidePost(@RequestParam("sowMethod") String sowMethod,
                            @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime,
                            @RequestParam("stage") String stage,
                            @RequestParam("detail") String detail,
                            @RequestParam("illness") String illness,
                            @RequestParam("ingredient") String ingredient,
                            HttpSession session) {
        recommend recommend = new recommend();
        recommendPesticide recommendPesticide = new recommendPesticide();
        recommend.setDetail(detail);
        recommend.setNotice("default");
        recommend.setRecommend_endtime(endTime);
        recommend.setRecommend_time(startTime);
        recommend.setSowmethod(new Converse().sowMethodReverse(sowMethod));
        recommend.setStage(stage);
        recommend.setRecommend_type(3);//农药是3
        recommend.setSeed_id(Integer.parseInt(session.getAttribute("seed_id").toString()));
        recommend.setSpecialist_id(Integer.parseInt(session.getAttribute("specialist_id").toString()));
        recommendRepository.insertJudgeBySeedId(recommend);
        //通过专家ID查询到最新的推荐ID
        int specialistId = Integer.parseInt(session.getAttribute("specialist_id").toString());
        recommendPesticide.setRecommend_id(recommendRepository.findRecommendIdBySpecialistId(specialistId));
        recommendPesticide.setIngredient(ingredient);
        recommendPesticide.setName(illness);
        recommendRepository.savePesticide(recommendPesticide);
        session.setAttribute("sowMethod",sowMethod);
        return "redirect:/specialistReco";
    }

    //查看全部信息的处理
    @GetMapping("/specialistSeedAll")
    public String getSpecialistSeedAll(
            HttpSession session,
            Model model
    ){
        int seed_id = Integer.parseInt(session.getAttribute("seed_id").toString());
        int specialist_id = Integer.parseInt(session.getAttribute("specialist_id").toString());
        specialistAndSeedId specialistAndSeedId = new specialistAndSeedId();
        specialistAndSeedId.setSeed_id(seed_id);
        specialistAndSeedId.setSpecialist_id(specialist_id);
        List<recommend> recommends =  recommendRepository.findBySeedIdAndSpecialistId(specialistAndSeedId);
//        model.addAttribute("crops_name",crops_name);
        model.addAttribute("recommends",recommends);//遍历出来的对应的推荐信息
        model.addAttribute("converse", new Converse());
        return "specialistReco/timebase";
    }

    @GetMapping("/connectUser")
    public String connectUserView(HttpSession session,Model model){
        String usertel=userRepository.findUserTel(session.getAttribute("userPhone").toString());
        List<String> names = userRepository.findUserName(session.getAttribute("userPhone").toString());
        List<questions> questions = questionRepository.findquestionByPhone(usertel);
        List<answers> answers = answerRepository.findAnswerByPhone(usertel);
        User user = userRepository.findByAccount(usertel);
        List<nameAndnumb> nAndn=new ArrayList<nameAndnumb>();
        for(int i=0;i<names.size();i++){
            String phone=userRepository.findPhoneByName(names.get(i));
            int numb=Integer.parseInt(questionRepository.getStateNumByPhone(phone));
            nameAndnumb a=new nameAndnumb();
            a.setName(names.get(i));
            a.setNumb(numb);
//            System.out.println(numb);
            nAndn.add(a);
        }
        model.addAttribute("user",user);
        model.addAttribute("questions",questions);
        model.addAttribute("answers",answers);
//        model.addAttribute("names",names);
        model.addAttribute("nameAndnumb",nAndn);
        return "connect/connectUser";
    }

//  专家提交回答
    @PostMapping("/specialanswer")
    public String getspecialAnswer(HttpServletRequest request,HttpSession session){
        String answer = request.getParameter("answer");
        String usertel=request.getParameter("usertel");
        String name = request.getParameter("name");
        session.setAttribute("username",name);
        answerRepository.saveAnswer(answer,0,usertel);
        return "redirect:/newAnswer";
    }
//  用户列表超链接
    @GetMapping("/newAnswer")
    public String getNewAnswer(HttpSession session, Model model,String name){
        if(name==null)
            name = session.getAttribute("username").toString();
        User user = userRepository.findByName(name);
        List<String> names = userRepository.findUserName(session.getAttribute("userPhone").toString());
        List<questions> questions = questionRepository.findquestionByPhone(user.getUser_tel());
        List<answers> answers = answerRepository.findAnswerByPhone(user.getUser_tel());
        List<nameAndnumb> nAndn=new ArrayList<nameAndnumb>();
        for(int i=0;i<names.size();i++){
            String phone=userRepository.findPhoneByName(names.get(i));
            int numb=Integer.parseInt(questionRepository.getStateNumByPhone(phone));
            nameAndnumb a=new nameAndnumb();
            a.setName(names.get(i));
            a.setNumb(numb);
            nAndn.add(a);
        }
        model.addAttribute("user",user);
        model.addAttribute("questions",questions);
        model.addAttribute("answers",answers);
//        model.addAttribute("names",names);
        model.addAttribute("nameAndnumb",nAndn);
        return "connect/connectUser";
    }
//  设置未读为已读
    @GetMapping("/setMessage")
    public String setMessage(HttpSession session,String id){
        questionRepository.setState(Integer.parseInt(id));
        String Phone = questionRepository.findPhoneById(Integer.parseInt(id));
        User user = userRepository.findByAccount(Phone);
        session.setAttribute("username",user.getUser_name());
        return "redirect:/newAnswer";
    }
//  删除用户问题
    @GetMapping("/deleteMessage")
    public String deleteMessage(HttpSession session,String id){
        String Phone = questionRepository.findPhoneById(Integer.parseInt(id));
        User user = userRepository.findByAccount(Phone);
        session.setAttribute("username",user.getUser_name());
        questionRepository.deleteQuestion(Integer.parseInt(id));
        return "redirect:/newAnswer";
    }
//  删除我的回答
    @GetMapping("/deleteMessage2")
    public String deleteMessage2(HttpSession session,String id){
        String Phone = answerRepository.findPhoneById(Integer.parseInt(id));
        User user = userRepository.findByAccount(Phone);
        session.setAttribute("username",user.getUser_name());
        answerRepository.deleteAnswer(Integer.parseInt(id));
        return "redirect:/newAnswer";
    }
}
class nameAndnumb{
    String name;
    Integer numb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }
}
