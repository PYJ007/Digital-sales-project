package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.browsingHistory;
import com.dajun.springbootplatform.entities.dealLobby.buyersChat;
import com.dajun.springbootplatform.entities.dealLobby.sellerChat;
import com.dajun.springbootplatform.repository.buyerschatRepository;
import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.repository.sellerchatRepository;
import com.dajun.springbootplatform.repository.userRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dealLobby")
public class dealController {
    static String userName;
    static String sType;
    static String currentPhone;
    @Resource
    seedRepository seedRepository;
    @Resource
    userRepository userRepository;
    @Resource
    buyerschatRepository buyerschatRepository;
    @Resource
    sellerchatRepository sellerchatRepository;
    @GetMapping("/index2")
    public String index(HttpSession session,String name,Model model){
        userName=name;
        session.setAttribute("userName",name);
        String userPhone = userRepository.findPhoneByName(name);
        session.setAttribute("userPhone",userPhone);
        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        List<Seed> seeds=seedRepository.findSeedByState();

        FileOutputStream fos;
        try {
            for (int i = 0; i < seeds.size(); i++) {
                byte[] buffer = seeds.get(i).getSeed_image();
                File file = new File("src/main/resources/static/img/seed" + seeds.get(i).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("seeds",seeds);
        List<browsingHistory> bH = seedRepository.findBrowsingHistory(userPhone);
        int limit = 0;
        if (bH.size()<3)
            limit = bH.size();
        else limit = 3;
        List<seedAndType> seedAndTypes = new ArrayList<>();
        for(int i=0;i<limit;i++){
            seedAndType sat = new seedAndType();
            sat.setType(bH.get(i).getSeed_type());
            sat.seeds = seedRepository.findRandSeedByType(bH.get(i).getSeed_type());
            seedAndTypes.add(sat);
        }
//        List<Seed> seeds2=seedRepository.findSeedFromBrowsingHistory(String.valueOf(session.getAttribute("userPhone")));

        try {
            for(int j = 0;j<seedAndTypes.size();j++) {
                for (int i = 0; i < seedAndTypes.get(j).seeds.size(); i++) {
                    byte[] buffer = seedAndTypes.get(j).seeds.get(i).getSeed_image();
                    File file = new File("src/main/resources/static/img/seed" + seedAndTypes.get(j).seeds.get(i).getSeed_id() + ".jpg");

                    fos = new FileOutputStream(file);
                    fos.write(buffer);
                    fos.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("seedAndTypes",seedAndTypes);
        String photo=String.valueOf(seeds.get(0).getSeed_id());
        int id=seeds.get(0).getSeed_id();
        String introduce=seeds.get(0).getSeed_introduce();
        model.addAttribute("photoname",photo);
        model.addAttribute("id",id);
        model.addAttribute("introduce",introduce);
        return "dealLobby/index";
    }

    @GetMapping("/shopCar")
    public String shopCar(Model model,HttpSession session){
        String name = session.getAttribute("userName").toString();
        List<Seed> seedList = seedRepository.findSeedFromCar(name);
        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        model.addAttribute("seedList",seedList);
        model.addAttribute("userName",name);
        return "dealLobby/shopCar";
    }

    //实现分页功能
    @GetMapping("/shopCar2")
    public String shopCar2(Model model,String star){
        String name = userName;
        int count=3;//每个页面显示一条数据
        int s;
        int seedNumb=seedRepository.findCount(name);
        int numb;
        if(seedNumb%count==0)
            numb=seedNumb/count;
        else numb=seedNumb/count+1;
        if(star==null||Integer.parseInt(star)<=0){
            model.addAttribute("star",1);
            star="0";
            s=Integer.parseInt(star);
        }else {
            if(Integer.parseInt(star)>=numb) {
                model.addAttribute("star",numb);
                s = numb - 1;
            }
            else {
                model.addAttribute("star", Integer.parseInt(star));
                s = Integer.parseInt(star) - 1;
            }
        }
        s=s*count;
        List<Seed> seedList = seedRepository.findSeedFromCar2(name,s,count);
        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        model.addAttribute("seedList",seedList);
        model.addAttribute("userName",name);
        model.addAttribute("numb",numb);
        return "dealLobby/shopCar";
    }

    @PostMapping("/shopSearch")
    public String shopSearch(Model model, HttpServletRequest request,String star){
        sType=request.getParameter("cereal");
        int count=3;//每个页面显示一条数据
        int s;
        int seedNumb=seedRepository.findCountByType(sType);
        int numb;
        if(seedNumb%count==0)
            numb=seedNumb/count;
        else numb=seedNumb/count+1;
        if(star==null||Integer.parseInt(star)<=0){
            model.addAttribute("star",1);
            star="0";
            s=Integer.parseInt(star);
        }else {
            if(Integer.parseInt(star)>=numb) {
                model.addAttribute("star",numb);
                s = numb - 1;
            }
            else {
                model.addAttribute("star", Integer.parseInt(star));
                s = Integer.parseInt(star) - 1;
            }
        }
        s=s*count;
        List<Seed> seeds = seedRepository.findSeedByType2(sType,s,count);

        FileOutputStream fos;
        try {
            for (int i = 0; i < seeds.size(); i++) {
                byte[] buffer = seeds.get(i).getSeed_image();
                File file = new File("src/main/resources/static/img/seed" + seeds.get(i).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        model.addAttribute("seedList",seeds);
        model.addAttribute("type",sType);
        model.addAttribute("numb",numb);
        return "dealLobby/shopSearch";
    }

    @GetMapping("/shopSearch3")
    public String shopSearch3(Model model, @RequestParam("cereal") String cereal, String star){
        System.out.println(cereal);
        sType=cereal;
//        sType=null;
        int count=3;//每个页面显示一条数据
        int s;
        int seedNumb=seedRepository.findCountByType(sType);
        int numb;
        if(seedNumb%count==0)
            numb=seedNumb/count;
        else numb=seedNumb/count+1;
        if(star==null||Integer.parseInt(star)<=0){
            model.addAttribute("star",1);
            star="0";
            s=Integer.parseInt(star);
        }else {
            if(Integer.parseInt(star)>=numb) {
                model.addAttribute("star",numb);
                s = numb - 1;
            }
            else {
                model.addAttribute("star", Integer.parseInt(star));
                s = Integer.parseInt(star) - 1;
            }
        }
        s=s*count;
        List<Seed> seeds = seedRepository.findSeedByType2(sType,s,count);

        FileOutputStream fos;
        try {
            for (int i = 0; i < seeds.size(); i++) {
                byte[] buffer = seeds.get(i).getSeed_image();
                File file = new File("src/main/resources/static/img/seed" + seeds.get(i).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        model.addAttribute("seedList",seeds);
        model.addAttribute("type",sType);
        model.addAttribute("numb",numb);
        return "dealLobby/shopSearch";
    }

    @GetMapping("/shopSearch2")
    public String shopSearch2(Model model,String star){
        int count=3;//每个页面显示一条数据
        int s;
        int seedNumb=seedRepository.findCountByType(sType);
        int numb;
        if(seedNumb%count==0)
            numb=seedNumb/count;
        else numb=seedNumb/count+1;
        if(star==null||Integer.parseInt(star)<=0){
            model.addAttribute("star",1);
            star="0";
            s=Integer.parseInt(star);
        }else {
            if(Integer.parseInt(star)>=numb) {
                model.addAttribute("star",numb);
                s = numb - 1;
            }
            else {
                model.addAttribute("star", Integer.parseInt(star));
                s = Integer.parseInt(star) - 1;
            }
        }
        s=s*count;
        List<Seed> seeds = seedRepository.findSeedByType2(sType,s,count);

        FileOutputStream fos;
        try {
            for (int i = 0; i < seeds.size(); i++) {
                byte[] buffer = seeds.get(i).getSeed_image();
                File file = new File("src/main/resources/static/img/seed" + seeds.get(i).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        model.addAttribute("seedList",seeds);
        model.addAttribute("type",sType);
        model.addAttribute("numb",numb);
        return "dealLobby/shopSearch";
    }

    @GetMapping("/returnIndex")
    public String returnIndex(HttpSession session,String username){
        String phone = userRepository.findPhoneByName(username);
        session.setAttribute("userName",username);
        session.setAttribute("userPhone",phone);
        session.setAttribute("userMsg",3);
        return "redirect:/index";
    }
    @GetMapping("/index")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/delete")
        public String delete( Integer id ,
                                     String star,
                                 Model model,
                                 HttpSession session){
        seedRepository.deleteShopCar(session.getAttribute("userName").toString(),id);
            String name = userName;
    int count=2;//每个页面显示一条数据
    int s;
    int seedNumb=seedRepository.findCount(name);
    int numb;
        if(seedNumb%count==0)
    numb=seedNumb/count;
        else numb=seedNumb/count+1;
        if(star==null||Integer.parseInt(star)<=0){
        model.addAttribute("star",1);
        star="0";
        s=Integer.parseInt(star);
    }else {
        if(Integer.parseInt(star)>=numb) {
            model.addAttribute("star",numb);
            s = numb - 1;
        }
        else {
            model.addAttribute("star", Integer.parseInt(star));
            s = Integer.parseInt(star) - 1;
        }
    }
    s=s*count;
    List<Seed> seedList = seedRepository.findSeedFromCar2(name,s,count);
    List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        model.addAttribute("seedList",seedList);
        model.addAttribute("userName",name);
        model.addAttribute("numb",numb);
        return "dealLobby/shopCar";
    }

    @GetMapping("/connect")
    public String connect(HttpSession session,Model model,String sellerPhone){
        List<sellerChat> sellerChats = null;
        List<buyersChat> buyersChats = null;
        List<User> users = null;
        List<nameAndnumb> nads = null;
        User seller = null;
        if(currentPhone!=null)
            sellerPhone=currentPhone;
        if (sellerPhone==null&&currentPhone==null)
            sellerPhone = buyerschatRepository.findSellerPhoneByByuerPhone(String.valueOf(session.getAttribute("userPhone")));
        if(sellerPhone!=null) {
            sellerChats = sellerchatRepository.findAnswerByPhone(sellerPhone,String.valueOf(session.getAttribute("userPhone")));
            buyersChats = buyerschatRepository.findQuistionByPhone(sellerPhone,String.valueOf(session.getAttribute("userPhone")));
            users = userRepository.findSellerBybuyerPhone(String.valueOf(session.getAttribute("userPhone")));
            nads = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                nameAndnumb an = new nameAndnumb();
                an.user = users.get(i);
                an.numb = sellerchatRepository.findMassageStateByPhone(users.get(i).getUser_tel(),String.valueOf(session.getAttribute("userPhone")));
                nads.add(an);
            }
            seller = userRepository.findByAccount(sellerPhone);
            model.addAttribute("buyerChats", buyersChats);
            model.addAttribute("sellerChats", sellerChats);
            model.addAttribute("seller", seller);
            model.addAttribute("nads", nads);
            User user = userRepository.findByAccount(String.valueOf(session.getAttribute("userPhone")));
            model.addAttribute("user", user);
            currentPhone=null;
        }
        else{
            model.addAttribute("buyerChats", buyersChats);
            model.addAttribute("sellerChats", sellerChats);
            model.addAttribute("seller", seller);
            model.addAttribute("nads", nads);
            User user = userRepository.findByAccount(String.valueOf(session.getAttribute("userPhone")));
            model.addAttribute("user", user);
        }
        List<String> seedType = seedRepository.getType();
        model.addAttribute("seedTypes",seedType);
        return "connect/connectSeller";
    }

    @GetMapping("/establish")
    public String establish(String sellerPhone,HttpSession session){
        if(buyerschatRepository.existSellerAndByuer(sellerPhone,String.valueOf(session.getAttribute("userPhone")))==0)
            buyerschatRepository.establishSellerAndByuer(sellerPhone,String.valueOf(session.getAttribute("userPhone")));
        return "redirect:/dealLobby/connect";
    }

    @PostMapping("/saveChat")
    public String saveChat(ServletRequest request, HttpSession session){
        String answer = request.getParameter("answer");
        currentPhone = request.getParameter("usertel");
        buyerschatRepository.saveAnswer(answer,0,request.getParameter("usertel"),String.valueOf(session.getAttribute("userPhone")));
        return "redirect:/dealLobby/connect";
    }

    class nameAndnumb{
        User user;
        Integer numb;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Integer getNumb() {
            return numb;
        }

        public void setNumb(Integer numb) {
            this.numb = numb;
        }
    }


}
class seedAndType{
    String type;
    List<Seed> seeds;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }
}