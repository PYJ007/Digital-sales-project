package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.Dao.Converse;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.repository.registerRepository;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
public class registerController {

    @Resource
    private registerRepository registerRepository;

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
        user.setUser_phone(phone);
        user.setUser_pass(password);
        user.setUser_name(name);
        session.setAttribute("userPhone",phone);
        registerRepository.saveUser(user);
        return "loginOrRegister/registerAlter";
    }

    @PostMapping(value = "/registerCrops") //用户第一次添加田间信息
    public String registerCrops(@RequestParam("user_card")String user_card,
                                @RequestParam("crop_prov")String crop_prov,
                                @RequestParam("crop_city")String crop_city,
                                @RequestParam("crop_country")String crop_country,
                                @RequestParam("crop_address")String crop_address,
                                @RequestParam("crop_name2")String crop_name1,
                                @RequestParam("crop_name2")String crop_name2,
                                @RequestParam("crop_acres")String acres,
                                HttpSession session
                                ){
        User user = new User();
        user.setUser_card(user_card);
        user.setUser_phone(session.getAttribute("userPhone").toString());
        user.setUser_cropsname(new Converse().cropTypeConverse(Integer.parseInt(crop_name1),Integer.parseInt(crop_name2)));
        user.setUser_cropstype(new Converse().cropNameConverse(Integer.parseInt(crop_name1)));
        user.setUser_cropsacres(acres);
        user.setUser_fieldacres(Double.parseDouble(acres));
        user.setUser_fieldadress(new Converse().cityConverse(Integer.parseInt(crop_prov),Integer.parseInt(crop_city))+" "+crop_country+" " +crop_address);
        registerRepository.saveCrops(user);
        session.setAttribute("numbers",0);
        return "loginOrRegister/loginTest";
    }
}
