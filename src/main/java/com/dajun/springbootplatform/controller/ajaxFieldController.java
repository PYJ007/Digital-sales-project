package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.repository.fieldRepository;
import com.dajun.springbootplatform.repository.recommendRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ajaxFieldController {
    @Resource
    private recommendRepository recommendRepository;
    @Resource
    private fieldRepository fieldRepository;
    @PostMapping("/addImportentRecord")
    public int addImportentRecord(HttpServletRequest request){
        Integer recommendId = Integer.valueOf(request.getParameter("recommendId"));
        Integer fieldId = Integer.valueOf(request.getParameter("fieldId"));
        Integer seedId = Integer.valueOf(request.getParameter("seedId"));
        int time = fieldRepository.findFieldTime(fieldId);
        String groupId = request.getParameter("fieldId")+"-"+String.valueOf(time);
        if(recommendRepository.recommendReadOrNot(recommendId,groupId)!=null){
            return 1;
        }
        else{
            Date date = new Date();
            fieldRepository.addImportentRecord(fieldId, recommendId, groupId, seedId, date);
            return 0;
        }
    }
}
