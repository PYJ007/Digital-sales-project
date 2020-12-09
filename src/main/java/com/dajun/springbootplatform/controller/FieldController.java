package com.dajun.springbootplatform.controller;


import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.other.phoneAndCropsName;
import com.dajun.springbootplatform.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FieldController {

    @Resource
    private fieldRepository fieldRepository;

    @Resource
    private registerRepository registerRepository;

    @Resource
    private recommendRepository recommendRepository;

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
            List<field> fields = fieldRepository.findFieldByTel(phone);
            model.addAttribute(fields);
        }
        //numbers为1代表没有完善信息
        else session.setAttribute("numbers",1);
        return "myfield";
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
}
