package com.dajun.springbootplatform.service.imp;

import com.dajun.springbootplatform.entities.valuate.candidateSeed;
import com.dajun.springbootplatform.repository.seedRepository;
import com.dajun.springbootplatform.service.candidateSeed2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class candidateSeedImp implements candidateSeed2 {
    @Autowired
    seedRepository seedRepository;
    @Override
    public void saveCandidate(ServletRequest request, HttpSession session, MultipartFile file) {
        candidateSeed candidateSeed = new candidateSeed();
        candidateSeed.setSeed_name(request.getParameter("name"));
        candidateSeed.setSeed_introduce(request.getParameter("introduce"));
        candidateSeed.setSeed_plantarea(request.getParameter("plantarea"));
        candidateSeed.setSeed_price(Integer.parseInt(request.getParameter("price")));
        candidateSeed.setSeed_method(request.getParameter("method"));
        candidateSeed.setSeed_manufacturer(String.valueOf(session.getAttribute("userName")));
        candidateSeed.setSeed_productiondate(String.valueOf(request.getParameter("time")));
        candidateSeed.setSeed_type(request.getParameter("type"));
        candidateSeed.setUser_phone(String.valueOf(session.getAttribute("userPhone")));
        InputStream is=null;
        try {
            is = file.getInputStream();
            byte[] pic = new byte[(int)file.getSize()];
            is.read(pic);
            candidateSeed.setSeed_image(pic);
            seedRepository.saveCandidate(candidateSeed);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<candidateSeed> getCandidate1(HttpSession session) {
        String phone = String.valueOf(session.getAttribute("userPhone"));
        return seedRepository.getCandidate1(phone);
    }

    @Override
    public List<candidateSeed> getCandidate2(HttpSession session) {
        String phone = String.valueOf(session.getAttribute("userPhone"));
        return seedRepository.getCandidate2(phone);
    }
}
