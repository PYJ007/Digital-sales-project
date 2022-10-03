package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.application.Converse;
import com.dajun.springbootplatform.entities.OtherUser;
import com.dajun.springbootplatform.entities.Specialist;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.other.cardAndAddress;
import com.dajun.springbootplatform.repository.*;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;


@Controller
public class registerController {

    @Resource
    private registerRepository registerRepository;

    @Resource
    private fieldRepository fieldRepository;

    @Resource
    private userRepository userRepository;

    @Resource
    private seedRepository seedRepository;

    @Resource
    private certificationRepository certificationRepository;

    @Resource
    private  otherUserRepository otherUserRepository;

    @Resource
    private specialistRepository specialistRepository;

    @RequestMapping("/register")
    public String reg(){
        return "loginOrRegister/register";
    }

    @RequestMapping("/farmerReg")
    public String farmerReg(Model model){
        //为添加作物信息提供接口
        //枸杞
        model.addAttribute("wolfBerry", seedRepository.findDetailNameByTypeName("枸杞"));
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
        return "loginOrRegister/farmerRegister";
    }

    @RequestMapping("/professorReg")
    public String professorReg(Model model){
        model.addAttribute("role",0);
        return "loginOrRegister/professorReg";
    }

    @GetMapping("/alter")
    public String alter(){
        return "loginOrRegister/registerAlter";
    }

    @PostMapping(value = "/registerSub")//注册成普通用户
    public String register(@RequestParam("userName")String name,
                           @RequestParam("userPhone")String phone,
                           @RequestParam("userPassword")String password,
                           Model model,
                           HttpSession session){
        User user = new User();
        user.setUser_tel(phone);
        user.setUser_pass(password);
        user.setUser_name(name);
        try{
            registerRepository.saveUser(user);
            session.setAttribute("userPhone",phone);
            //numbers为1代表用户还没有完善信息
            return "loginOrRegister/registerAlter";
        }catch (Exception e){
            model.addAttribute("msg","手机号码已存在");
        }
        return "loginOrRegister/register";
    }

    //注册成专家
    @PostMapping(value = "/registerSpecialist") //用户第一次添加田间信息(一个田间信息、田的地址、身份证)
    public String registerSpecialist(@RequestParam("user_prov")String crop_prov,//用户的省
                                     @RequestParam("user_city")String crop_city,//用户的市
                                     @RequestParam("user_country")String crop_country,//用户的街道
                                     @RequestParam("user_address")String crop_address,//具体地址
                                     @RequestParam("intro")String intro,//作物的类别
                                     @RequestParam("domain")String domain,//作物的名称
                                     @RequestParam("role") Integer role,
                                     @RequestParam("file") MultipartFile pic, HttpSession session) throws IOException {
        String user_tel = session.getAttribute("userPhone").toString();
        User user = userRepository.findByAccount(user_tel);
        Specialist specialist = new Specialist();
        specialist.setSpecialist_address(new Converse().cityConverse(Integer.parseInt(crop_prov),Integer.parseInt(crop_city))+" "+crop_country+" " +crop_address);
        specialist.setSpecialist_instructions(intro);
        specialist.setSpecialist_name(user.getUser_name());
        specialist.setSpecialist_pass(user.getUser_pass());
        specialist.setSpecialist_phone(user_tel+"_forbid");
        specialist.setSpecialist_type(domain);
        registerRepository.saveSpecialist(specialist);
        int id = specialist.getSpecialist_id();
        if (role==0){//专家
            if (id>1000) {
                String newId = id+"";
                registerRepository.updateSpecialistId(id,newId.split("0")[newId.split("0").length-1]);
            }
        }
        else if(role==1){//推广员
            System.out.println(id);
            if (id<1000 ) registerRepository.updateSpecialistId(id,"1000"+id);
            if (id>2000 ) {
                String newId = id+"";
                registerRepository.updateSpecialistId(id,"1000"+newId.split("0")[newId.split("0").length-1]);
            }
        }
        else if(role==2){//供应商
            if (id<1000 ) registerRepository.updateSpecialistId(id,"2000"+id);
            else {
                String newId = id+"";
                registerRepository.updateSpecialistId(id,"2000"+newId.split("0")[newId.split("0").length-1]);
            }
        }
        //删除临时的用户表
        userRepository.deleteById(user_tel);
        certificationRepository.insertCertification(specialistRepository.findIdByPhone(user_tel+"_forbid"),pic.getBytes());
        return "loginOrRegister/loginTest";
    }

    @GetMapping("/register/other")
    public String getRegister(@RequestParam("role") int role,Model model){
        model.addAttribute("role",role);
        return "loginOrRegister/otherReg";
    }

    @PostMapping("/otherUser")
    public String otherUser(@RequestParam("user_prov")String crop_prov,//用户的省
                            @RequestParam("user_city")String crop_city,//用户的市
                            @RequestParam("user_country")String crop_country,//用户的街道
                            @RequestParam("user_address")String crop_address,//具体地址
                            @RequestParam("intro")String intro,//作物的类别
                            @RequestParam("company")String company,//作物的名称
                            @RequestParam("role") Integer role,
                            @RequestParam("file") MultipartFile pic, HttpSession session) throws IOException {
        String user_tel = session.getAttribute("userPhone").toString();
        String address = new Converse().cityConverse(Integer.parseInt(crop_prov),Integer.parseInt(crop_city))+" "+crop_country+" " +crop_address;
        User user = userRepository.findByAccount(user_tel);
        OtherUser otherUser = new OtherUser();
        otherUser.setAddress(address);
        otherUser.setCompany(company);
        otherUser.setIntro(intro);
        otherUser.setPassword(user.getUser_pass());
        otherUser.setPhone(user.getUser_tel());
        otherUser.setPic(pic.getBytes());
        otherUser.setRole(role);
        userRepository.deleteById(user_tel);
        otherUserRepository.insertOtherUser(otherUser);
        return "loginOrRegister/loginTest";
    }



    @PostMapping(value = "/registerCrops") //用户第一次添加田间信息(一个田间信息、田的地址、身份证)
    public String registerCrops(@RequestParam("user_card")String user_card,
                                @RequestParam("user_prov")String crop_prov,//用户的省
                                @RequestParam("user_city")String crop_city,//用户的市
                                @RequestParam("user_country")String crop_country,//用户的街道
                                @RequestParam("user_address")String crop_address,//具体地址
                                @RequestParam("CropType")String crop_name1,//作物的类别
                                @RequestParam("CropName")String crop_name2,//作物的名称
                                @RequestParam("crop_acres")Double acres,//作物对应的亩数
                                HttpSession session
                                ){
        //身份证和地址信息
        cardAndAddress cardAndAddress = new cardAndAddress();
        String user_tel = session.getAttribute("userPhone").toString();
        String fieldAddress = new Converse().cityConverse(Integer.parseInt(crop_prov),Integer.parseInt(crop_city))+" "+crop_country+" " +crop_address;
        cardAndAddress.setUser_card(user_card);
        cardAndAddress.setUser_fieldadress(fieldAddress);
        cardAndAddress.setUser_tel(user_tel);
        registerRepository.saveCardAndAddress(cardAndAddress);
        //第一次添加的田间信息
        field field = new field();
        field.setUser_cropsacres(acres);
        field.setUser_cropsname(crop_name2);
        field.setUser_cropstype(crop_name1);
        field.setUser_tel(user_tel);
        registerRepository.insertField(field);
        //更新用户的总亩数
        User user = new User();
        user.setUser_fieldacres(acres);
        user.setUser_tel(user_tel);
        fieldRepository.updateAcres(user);
        //这个number是用来判断用户是否完善了信息
        session.removeAttribute("number");
        return "loginOrRegister/loginTest";
    }

    //这里是农业生产者进一步添加田间信息
    @PostMapping(value = "/registerCropFurther")
    public String registerCrop(@RequestParam("CropName")String cropName,//作物名称，注意需要转化
                               @RequestParam("CropType")String cropType,//作物类型，同上
                               @RequestParam("CropAcres")Double cropAcres,//单个田的亩数
                               Model model,
                               HttpSession session){
        String phone = session.getAttribute("userPhone").toString();
        //创建田的信息实例，并插入
        field field = new field();
        field.setUser_tel(phone);
        field.setUser_cropstype(cropType);
        field.setUser_cropsname(cropName);
        field.setUser_cropsacres(cropAcres);
        registerRepository.insertField(field);
        //更新用户总亩数信息
        User user = new User();
        user.setUser_tel(phone);
        user.setUser_fieldacres(cropAcres);
        fieldRepository.updateAcres(user);
        return "redirect:/myField";
    }
}
