package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.Dao.Converse;
import com.dajun.springbootplatform.entities.other.specialistAndSeedId;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.entities.recommendPesticide;
import com.dajun.springbootplatform.repository.recommendRepository;
import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.repository.specialistRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class specialistController {

//    营养元素顺序
    private static List<String> elements = Arrays.asList("钾","氮","磷");

    @Resource
    private seedRepository seedRepository;

    @Resource
    private recommendRepository recommendRepository;

    @Resource
    private specialistRepository specialistRepository;

    @GetMapping("/specialistIndex")
    public String getSpecialistIndex(){
        return "specialistIndex";
    }

    @GetMapping("/recommendFirst")
    public String getRecommendFirst(){
        return "recommendFirst";
    }

//  处理删除和更改请求
    @PostMapping(value = "/specialistForm")
    public String getSpecialistForm(
            @Param("operation") String operation,
            @Param("recommend_id") String recommend_id,
            Model model){
        if (operation.equals("update")){
            model.addAttribute("recommend_id",recommend_id);
            return "index";
        }
        else {
            recommendRepository.deleteByRecommendId(Integer.parseInt(recommend_id));
            return "redirect:/specialistReco";
        }
    }


//    处理提交的作物名称信息
    @GetMapping("/specialistFirst")
    public String getSpecialistFirst(String crops_name,
                                     HttpSession session){
        session.setAttribute("crops_name",crops_name);
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
        recommendRepository.insertBySeedId(recommend);
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
        recommendRepository.insertBySeedId(recommend);
        for (int i=0;i<ingredient.size();i++){
            if (ingredient.get(i).equals("true")){
                recommendElements.setElement(elements.get(i));
                recommendElements.setElement_type(ingredient_type.get(i));
                recommendElements.setElement_volume(ingredient_volume.get(i));
                //通过专家ID查询到最新的推荐ID
                recommendElements.setRecommend_id(recommendRepository.findRecommendIdBySpecialistId(specialistId));
                recommendRepository.saveElements(recommendElements);
            }
        }
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
        recommendRepository.insertBySeedId(recommend);
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
}
