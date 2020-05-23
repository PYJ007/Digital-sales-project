package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.entities.Seed;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface collectionProductRepository {
    List<Seed> findSeed(String collect_list);
    List<Fertilizer> findFertilizer(String collect_list);
}
