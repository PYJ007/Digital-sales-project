package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.application.Converse;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.other.cardAndAddress;
import com.dajun.springbootplatform.repository.fieldRepository;
import com.dajun.springbootplatform.repository.registerRepository;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
public class registerController {

    @Resource
    private registerRepository registerRepository;

    @Resource
    private fieldRepository fieldRepository;

    @RequestMapping("/register")
    public String reg(){
        return "loginOrRegister/register";
    }

    @RequestMapping("/farmerReg")
    public String farmerReg(){
        return "loginOrRegister/farmerRegister";
    }

    @RequestMapping("/professorReg")
    public String professorReg(){
        return "loginOrRegister/professorReg";
    }

    @PostMapping(value = "/registerSub")//注册成普通用户
    public String register(@RequestParam("userName")String name,
                           @RequestParam("userPhone")String phone,
                           @RequestParam("userPassword")String password,
                           HttpSession session){
        User user = new User();
        user.setUser_tel(phone);
        user.setUser_pass(password);
        user.setUser_name(name);
        session.setAttribute("userPhone",phone);
        //numbers为1代表用户还没有完善信息
        session.setAttribute("numbers",1);
        registerRepository.saveUser(user);
        return "loginOrRegister/registerAlter";
    }

    @PostMapping(value = "/registerCrops") //用户第一次添加田间信息(一个田间信息、田的地址、身份证)
    public String registerCrops(@RequestParam("user_card")String user_card,
                                @RequestParam("user_prov")String crop_prov,//用户的省
                                @RequestParam("user_city")String crop_city,//用户的市
                                @RequestParam("user_country")String crop_country,//用户的街道
                                @RequestParam("user_address")String crop_address,//具体地址
                                @RequestParam("crop_name1")String crop_name1,//作物的类别
                                @RequestParam("crop_name2")String crop_name2,//作物的名称
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
        String cropsName = new Converse().cropTypeConverse(Integer.parseInt(crop_name1),Integer.parseInt(crop_name2));
        String cropsType = new Converse().cropNameConverse(Integer.parseInt(crop_name1));
        field.setUser_cropsacres(acres);
        field.setUser_cropsname(cropsName);
        field.setUser_cropstype(cropsType);
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
    public String registerCrop(@RequestParam("CropName")Integer cropName,//作物名称，注意需要转化
                               @RequestParam("CropType")Integer cropType,//作物类型，同上
                               @RequestParam("CropAcres")Double cropAcres,//单个田的亩数
                               Model model,
                               HttpSession session){
        String phone = session.getAttribute("userPhone").toString();
        //创建田的信息实例，并插入
        field field = new field();
        String crop_name = new Converse().cropTypeConverse(cropType,cropName);
        String crop_type = new Converse().cropNameConverse(cropType);
        field.setUser_tel(phone);
        field.setUser_cropstype(crop_type);
        field.setUser_cropsname(crop_name);
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
