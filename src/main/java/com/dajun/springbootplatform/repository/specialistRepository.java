package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Specialist;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface specialistRepository {
    String findName(String phone);
    Specialist findByAccount(String phone);
    int findIdByPhone(String phone);
}
