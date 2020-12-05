package com.dajun.springbootplatform.repository;

import org.springframework.stereotype.Component;

@Component
public interface seedRepository {
    int findSeedIdByName(String name);
}
