package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.valuate.valuation;
import org.springframework.stereotype.Repository;

@Repository
public interface valuationRepository {
    void saveValuation(valuation valuation);

    valuation findValuationById(Integer id);
}
