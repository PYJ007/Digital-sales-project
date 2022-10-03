package com.dajun.springbootplatform.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.dealLobby.buyersChat;
import com.dajun.springbootplatform.entities.dealLobby.sellerChat;
import com.dajun.springbootplatform.entities.valuate.candidateSeed;
import com.dajun.springbootplatform.repository.buyerschatRepository;
import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.repository.sellerchatRepository;
import com.dajun.springbootplatform.repository.userRepository;
import com.dajun.springbootplatform.service.candidateSeed2;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/personalCenter")
public class personalCenter {
    static String currentPhone;//记录当前正在聊天的用户
    @Autowired
    candidateSeed2 candidateSeed2;
    @Resource
    seedRepository seedRepository;
    @Resource
    userRepository userRepository;
    @Resource
    sellerchatRepository sellerchatRepository;
    @Resource
    buyerschatRepository buyerschatRepository;
    @GetMapping("index")
    public String index(Model model, HttpSession session){
        String userName = String.valueOf(session.getAttribute("userName"));
        User user=userRepository.findByName(userName);
        List<Seed> seeds = seedRepository.findSeedByPhone(user.getUser_tel());

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

        model.addAttribute("user",user);
        model.addAttribute("seeds",seeds);
        return "dealLobby/personalCenter";
        }

    @GetMapping("/connect")
    public String connect(HttpSession session,Model model,String buyerPhone){
        List<sellerChat> sellerChats = null;
        List<buyersChat> buyersChats = null;
        List<User> users = null;
        List<nameAndnumb> nads = null;
        User buyer = null;
        if(currentPhone!=null)
            buyerPhone=currentPhone;
        if (buyerPhone==null&&currentPhone==null)
            buyerPhone = sellerchatRepository.findBuyerPhoneBySellerPhone(String.valueOf(session.getAttribute("userPhone")));
        if(buyerPhone!=null) {
            sellerChats = sellerchatRepository.findAnswerByPhone(String.valueOf(session.getAttribute("userPhone")), buyerPhone);
            buyersChats = buyerschatRepository.findQuistionByPhone(String.valueOf(session.getAttribute("userPhone")), buyerPhone);
            users = userRepository.findBuyerBySellerPhone(String.valueOf(session.getAttribute("userPhone")));
            nads = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                nameAndnumb an = new nameAndnumb();
                an.user = users.get(i);
                an.numb = buyerschatRepository.findMassageStateByPhone(String.valueOf(session.getAttribute("userPhone")), users.get(i).getUser_tel());
                nads.add(an);
            }
            buyer = userRepository.findByAccount(buyerPhone);
            model.addAttribute("buyerChats", buyersChats);
            model.addAttribute("sellerChats", sellerChats);
            model.addAttribute("buyer", buyer);
            model.addAttribute("nads", nads);
            User user = userRepository.findByAccount(String.valueOf(session.getAttribute("userPhone")));
            model.addAttribute("user", user);
            currentPhone=null;
        }
        else{
            model.addAttribute("buyerChats", buyersChats);
            model.addAttribute("sellerChats", sellerChats);
            model.addAttribute("buyer", buyer);
            model.addAttribute("nads", nads);
            User user = userRepository.findByAccount(String.valueOf(session.getAttribute("userPhone")));
            model.addAttribute("user", user);
        }
        return "connect/connectBuyer";
    }

    @GetMapping("/addCrop")
    public String addCrop(HttpSession session,Model model){
        List<String> types = seedRepository.getType();
        model.addAttribute("types",types);
        return "dealLobby/addCrop";
    }

    @PostMapping("/saveCandidataSeed")
    public String saveCandidateSeed(ServletRequest request, HttpSession session,@RequestParam("file") MultipartFile file,Model model){
        candidateSeed2.saveCandidate(request,session,file);
        String phone = String.valueOf(session.getAttribute("userPhone"));
        List<candidateSeed> candidateSeeds1 = seedRepository.getCandidate1(phone);
        List<candidateSeed> candidateSeeds2 = seedRepository.getCandidate2(phone);
        FileOutputStream fos;
        try {
            for (int i = 0; i < candidateSeeds1.size(); i++) {
                byte[] buffer = candidateSeeds1.get(i).getSeed_image();
                File file2 = new File("src/main/resources/static/img/seed" + candidateSeeds1.get(i).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file2);
                fos.write(buffer);
                fos.close();
            }
            for (int j = 0; j < candidateSeeds2.size(); j++) {
                byte[] buffer = candidateSeeds2.get(j).getSeed_image();
                File file2 = new File("src/main/resources/static/img/seed" + candidateSeeds2.get(j).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file2  );
                fos.write(buffer);
                fos.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("c1s",candidateSeeds1);
        model.addAttribute("c2s",candidateSeeds2);
        return "dealLobby/check";
    }

    @PostMapping("/saveChat")
    public String saveChat(ServletRequest request,HttpSession session){
        String answer = request.getParameter("answer");
        currentPhone = request.getParameter("usertel");
        sellerchatRepository.saveAnswer(answer,0,String.valueOf(session.getAttribute("userPhone")),request.getParameter("usertel"));
        return "redirect:/personalCenter/connect";
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

    @GetMapping("/getCandidate")
    public String getCandidate(Model model,HttpSession session){
        String phone = String.valueOf(session.getAttribute("userPhone"));
        List<candidateSeed> candidateSeeds1 = seedRepository.getCandidate1(phone);
        List<candidateSeed> candidateSeeds2 = seedRepository.getCandidate2(phone);
        FileOutputStream fos;
        try {
            for (int i = 0; i < candidateSeeds1.size(); i++) {
                byte[] buffer = candidateSeeds1.get(i).getSeed_image();
                File file = new File("src/main/resources/static/img/seed" + candidateSeeds1.get(i).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
            for (int j = 0; j < candidateSeeds2.size(); j++) {
                byte[] buffer = candidateSeeds2.get(j).getSeed_image();
                File file = new File("src/main/resources/static/img/seed" + candidateSeeds2.get(j).getSeed_id() + ".jpg");

                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("c1s",candidateSeeds1);
        model.addAttribute("c2s",candidateSeeds2);
        return "dealLobby/check";
    }
}
