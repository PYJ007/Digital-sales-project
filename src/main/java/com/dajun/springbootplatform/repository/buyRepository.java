package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.buy;
import org.springframework.stereotype.Component;

@Component
public interface buyRepository {
    public buy findbuy();
}
