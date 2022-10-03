package com.dajun.springbootplatform.service;

import com.dajun.springbootplatform.entities.valuate.candidateSeed;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface candidateSeed2 {
    void saveCandidate(ServletRequest request, HttpSession session, MultipartFile file);

    List<candidateSeed> getCandidate1(HttpSession session);

    List<candidateSeed> getCandidate2(HttpSession session);
}
