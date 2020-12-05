package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.Dao.Converse;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.UserFieldInfo;
import com.dajun.springbootplatform.repository.fieldRepository;
import com.dajun.springbootplatform.repository.registerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FieldController {

    @Resource
    private fieldRepository fieldRepository;

    @Resource
    private registerRepository registerRepository;

    @RequestMapping("/myField")
    public String filed(HttpSession session){
        User user = new User();
        List<UserFieldInfo> userFieldInfos = new ArrayList<>();
        user = fieldRepository.showField(session.getAttribute("userPhone").toString());
        if (user.getUser_cropsacres()!=null&&user.getUser_cropstype()!=null) {
            String[] field_acres = user.getUser_cropsacres().split(",");
            String[] crops_name = user.getUser_cropsname().split(",");
            String[] crops_type = user.getUser_cropstype().split(",");
            int flags = 0;
            for (int i = 0; i < field_acres.length; i++) {
                UserFieldInfo ufi = new UserFieldInfo();
                ufi.setUser_cropsacres(field_acres[i]);
                ufi.setUser_cropsname(crops_name[i]);
                ufi.setUser_cropstype(crops_type[i]);
                userFieldInfos.add(ufi);
                flags = i + 1;
            }
            session.setAttribute("numbers", flags);
            session.setAttribute("field_acres",user.getUser_fieldacres());
            session.setAttribute("field_address", user.getUser_fieldadress());
            session.setAttribute("userFieldInfos", userFieldInfos);
        }
        else session.setAttribute("isNull",1);
        return "myfield";
    }

    @PostMapping(value = "/registerCropFurther")
    public String registerCrop(@RequestParam("CropName")Integer cropName,
                               @RequestParam("CropType")Integer cropType,
                               @RequestParam("CropAcres")String cropAcres,
                               HttpSession session){
        String user_cropsname;
        double user_fieldacres; //这是总亩数
        String user_cropstype;
        String user_cropsacres; //这是单个亩数，逗号隔开
        User oldUser = fieldRepository.showField(session.getAttribute("userPhone").toString());
        User user = new User();
        user_cropsacres = oldUser.getUser_cropsacres();
        user_cropsname = oldUser.getUser_cropsname();
        user_cropstype = oldUser.getUser_cropstype();
        user_fieldacres = oldUser.getUser_fieldacres();
        user.setUser_phone(session.getAttribute("userPhone").toString());
        user.setUser_cropsacres(user_cropsacres+","+cropAcres);
        user.setUser_fieldacres(user_fieldacres+Double.parseDouble(cropAcres));
        user.setUser_cropsname(user_cropsname+","+ new Converse().cropTypeConverse(cropType,cropName));
        user.setUser_cropstype(user_cropstype+","+new Converse().cropNameConverse(cropType));
//        System.out.println(user.getUser_cropsacres()+"\n"+user.getUser_cropsacres()+"\n"+user.getUser_phone());
        registerRepository.saveCrop(user);
        return "loginOrRegister/farmerRegister";
    }

}
