package com.dajun.springbootplatform.controller;


import com.dajun.springbootplatform.application.computeFieldRec;
import com.dajun.springbootplatform.application.findRecommend;
import com.dajun.springbootplatform.entities.*;
import com.dajun.springbootplatform.entities.other.phoneAndCropsName;
import com.dajun.springbootplatform.entities.other.recommendIdAndTel;
import com.dajun.springbootplatform.repository.*;
import com.dajun.springbootplatform.service.FertilizerServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class FieldController {

    @Resource
    private fieldRepository fieldRepository;

    @Resource
    private recommendRepository recommendRepository;

    @Resource
    private seedRepository seedRepository;

    @Resource
    private specialistRepository specialistRepository;

    @Resource
    private findRecommend findRecommend;

    @Resource
    private userRepository userRepository;

    @Resource
    private FertilizerServiceImpl fertilizerService;


//这里是在用户进入我的田之前的网页，主要目的是让用户选择自己的种植方 作废，因为用户可能不止一种作物，不止一种种植方式，这个行不通

    //进入我的田的农时推荐，所有种植方式都会被展示出来
    @GetMapping("/myFieldReco")
    public String chooseMethod(Model model,
                               HttpSession session, HttpServletRequest request, @RequestParam("fieldId") String fieldId){
        model.addAttribute("fieldId",fieldId);
        String phone = session.getAttribute("userPhone").toString();
        //获取用户的总亩数
        Double acres = userRepository.findByAccount(phone).getUser_fieldacres();
        //农时推荐的信息
        List<recommend> recommendList = recommendRepository.findRecommendByCropsAndTime(phone,fieldId);
        //user对象和化肥对象，为了转化出推荐
        User user= userRepository.findByAccount(phone);
        if (user.getUser_fieldadress()!=null&&user.getUser_fieldacres()!=0) {
            //遍历获取用户的田间信息
            List<field> fields = fieldRepository.findMyField(phone);
            model.addAttribute("fieldList",fields);
        }
        List<Fertilizer> fertilizers = fertilizerService.selectfertilizerList();
        computeFieldRec computeFieldRec = new computeFieldRec();
        int useTime = fieldRepository.findFieldTime(Integer.valueOf(fieldId));
        String groupId = fieldId+"-"+String.valueOf(useTime);
        for (recommend recommend : recommendList) {

            if (recommendRepository.recommendReadOrNot(recommend.getRecommend_id(),groupId)!=null)
                recommend.setRecommend_readed(1);//判断有没有，有就是已经读啦

            //我吐了，为了那个化肥推荐，只能利用上化肥表中的notice字段了
            if (recommend.getRecommend_type()==2){//如果是化肥
                Map<String,Object> map = computeFieldRec.selectfertilizer(user,recommendRepository.findElementsById(recommend.getRecommend_id()),fertilizers);
                String notice = "推荐使用化肥：<"+map.get("fertilizer_Name")+">，预计使用化肥<"+map.get("kg_need")+
                        ">KG，"+"预计花费<"+map.get("money_need")+">元。";

                recommend.setNotice(notice);
            }
        }
        model.addAttribute(recommendList);
        //用与将种植方式转换出来
        model.addAttribute(specialistRepository);//专家名字转换
        model.addAttribute(seedRepository);//种子名称转化
        //用于将化肥信息转换出来
        model.addAttribute(findRecommend);
        //将总亩数
        model.addAttribute("acres",acres);
        return "myField/myfieldReco";
    }

    //更新是否已读(就是向recommend read 插入数据)
    @PostMapping(value = "/updateRead")
    public String updateRecommendRead(@RequestParam("recommendId") Integer recommendId,
                                      HttpSession session){
        String phone = session.getAttribute("userPhone").toString();
        recommendRepository.insertRecommendRead(new recommendIdAndTel(phone,recommendId));
        return "redirect:/myFieldReco";
    }

    //这里是请求我的田，用户田间信息、农时信息都在这里处理
    @GetMapping("/myField")
    public String field(HttpSession session,
                        Model model){
        //田的信息
        String phone = session.getAttribute("userPhone").toString();
        User user = fieldRepository.showField(phone);
        model.addAttribute(user);
        //只有当用户的总亩数而且田间的信息不为空的时候
        if (user.getUser_fieldadress()!=null&&user.getUser_fieldacres()!=0) {
            //遍历获取用户的田间信息
            List<field> fields = fieldRepository.findMyField(phone);
            model.addAttribute("fieldList",fields);
        }
        //numbers为1代表没有完善信息
        else session.setAttribute("numbers",1);
        List<Seed> seeds = seedRepository.findAllSeed();
        model.addAttribute("seeds",seeds);
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
        return "myField/myfield";
    }

    //处理用户删除田间信息
    //无语，我这里需要处理删除后更新用户的面积的
    @PostMapping(value = "/deleteFieldInfo")
    public String deleteFieldInfo(@RequestParam("cropsName") String cropsName,
            HttpSession session){
        String phone = session.getAttribute("userPhone").toString();
        phoneAndCropsName phoneAndCropsName = new phoneAndCropsName(phone,cropsName);
        //查询要删除的田的亩数
        double acres =  fieldRepository.selectFieldAcresByTelAndName(phoneAndCropsName);
        User user = new User();
        user.setUser_fieldacres(-acres);
        user.setUser_tel(phone);
        //将用户的亩数更新
        fieldRepository.updateAcres(user);
        //删除对应的田的信息
        fieldRepository.deleteFieldByTelAndName(phoneAndCropsName);
        return "redirect:/myField";
    }

    @PostMapping("/updateField")
    public String updateField(HttpServletRequest request){
        String seedName = request.getParameter("seedName");
        String seedType = seedRepository.findTypeBySeedName(seedName);
        int fieldId = Integer.valueOf(request.getParameter("fieldId"));
        fieldRepository.updateFieldCrop(seedName,seedType,fieldId);
        return "redirect:/myField";
    }
}
