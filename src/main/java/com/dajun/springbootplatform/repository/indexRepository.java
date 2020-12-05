package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.indexInfo;
import com.dajun.springbootplatform.entities.indexProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface indexRepository {
    List<indexProduct> findAllProduct();
    List<indexInfo> findAllInfo();
}
