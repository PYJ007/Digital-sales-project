package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.application.createSeedNo;
import com.dajun.springbootplatform.application.imgInput;
import com.dajun.springbootplatform.config.OssUtil;
import com.dajun.springbootplatform.entities.Seed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dajun.springbootplatform.repository.*;

import static com.dajun.springbootplatform.application.staticVariety.imgPath;


@Controller
public class seedController {

    @Resource
    private seedRepository seedRepository;

    @Resource
    private OssUtil ossUtil;

    //来到种子信息页面的请求
    @GetMapping("/seedInfo")
    public String seedInfo(Model model){
        List<Seed> seedList = seedRepository.findAllSeed();
        model.addAttribute(seedList);
        return "seedInfo";
    }

    //接收删除种子信息的请求
    @PostMapping(value = "/deleteSeedInfo")
    public String deleteSeedInfo(@RequestParam("seedId") int seedId){
        seedRepository.deleteSeedById(seedId);
        return "redirect:/seedInfo";
    }

    //接收种子信息
    //接受前端上传图片并保存
//    @PostMapping(value = "/insertSeedInfo")
//    public String insertSeedInfo(@RequestParam("seedImage") MultipartFile file,
//                                 @RequestParam("seedName") String seedName,
//                                 @RequestParam("seedIntroduce") String seedIntroduce,
//                                 @RequestParam("location") ArrayList<String> locations,
//                                 @RequestParam("seedMethod") String seedMethod,
//                                 @RequestParam("seedNote") String seedNote,
//                                 @RequestParam("seedPlantnumber") String seedPlantnumber,
//                                 @RequestParam("seedType") String seedType,
//                                 @RequestParam("seedManufacturer") String seedManufacturer,
//                                 @RequestParam("seedPhone") String seed_phone,
//                                 HttpSession session) throws IOException {
//        //图片名称先一律命名为 种子名+jpg
//        String fileName=file.getOriginalFilename();
//        String expName=fileName.substring(fileName.indexOf(".")+1);
//        String imgName = seedName + "." +expName;
//        //获取userController放入的专家手机号
//        String specialistPhone = session.getAttribute("userPhone").toString();
//        String url = ossUtil.upload(file,imgName);
//        //获取userController放入的专家ID
//        int specialistId = Integer.parseInt(session.getAttribute("specialist_id").toString());
//        Seed seed = new Seed();
//        seed.setSeed_name(seedName);
//        seed.setSpecialist_id(specialistId);
//        createSeedNo createSeedNo = new createSeedNo();
//        seed.setSeed_note(createSeedNo.create(seedType));
//        seed.setSeed_manufacturer(seedManufacturer);
//        seed.setSeed_phone(seed_phone);
//        seed.setSeed_image("https://dajunbucket.oss-cn-beijing.aliyuncs.com/"+url);//图片的路径
//        seed.setSeed_introduce(seedIntroduce);
//        //将地点循环遍历出来
//        StringBuilder location = new StringBuilder();
//        for (String str:locations){
//            location.append(" ").append(str);}
//        //存入
//        seed.setSeed_plantarea(location.toString());
//        seed.setSeed_method(seedMethod);
//        seed.setSeed_plantnumber(seedPlantnumber);
//        seed.setSeed_type(seedType);
//        //写入数据库
//        seedRepository.saveSeedInfo(seed);
//        String newId = "21000"+seed.getSeed_id();
//        if (seed.getSeed_id()<21000) seedRepository.updateSeedId(seed.getSeed_id(),newId);
//        //我更改id之后.id就不会从当前的位置自增了，而是从21000啥的开始所以要更改一下
//        return "redirect:/seedInfo";
//    }



}
