package com.dajun.springbootplatform.service;


import com.dajun.springbootplatform.entities.Fertilizer;
import com.dajun.springbootplatform.repository.FertilizerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class FertilizerServiceImpl {

    @Autowired
    FertilizerMapper fertilizerMapper;

    public List<Fertilizer> selectfertilizerList() {
        return fertilizerMapper.selecthuafeilistbynull();
    }

    public void agriMechineinsert(String fertilizerName,
                                  String fertilizerType,
                                  String fertilizerN,
                                  String fertilizerP,
                                  String fertilizerK,
                                  String fertilizerOther,
                                  String fertilizerInstructions,
                                  String fertilizerPrice,
                                  String fertilizerManufacturer,
                                  String fertilizerPhone,
                                  String fertilizerAddress,
                                  Date fertilizerProductiondate,
                                  String fertilizerShelflife,
                                  String fertilizerModeratecrop,
                                  Date recommendData) {
        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setFertilizer_address(fertilizerAddress);
        fertilizer.setFertilizer_instructions(fertilizerInstructions);
        fertilizer.setFertilizer_name(fertilizerName);
        fertilizer.setFertilizer_type(Integer.parseInt(fertilizerType));
        fertilizer.setFertilizer_n(fertilizerN);
        fertilizer.setFertilizer_p(fertilizerP);
        fertilizer.setFertilizer_k(fertilizerK);
        fertilizer.setFertilizer_other(fertilizerOther);
        fertilizer.setFertilizer_price(Double.parseDouble(fertilizerPrice));
        fertilizer.setFertilizer_manufacturer(fertilizerManufacturer);
        fertilizer.setFertilizer_phone(fertilizerPhone);
        fertilizer.setFertilizer_productiondate(fertilizerProductiondate);
        fertilizer.setFertilizer_manufacturer(fertilizerManufacturer);
        fertilizer.setFertilizer_shelflife(Integer.parseInt(fertilizerShelflife));
        fertilizer.setFertilizer_moderatecrop(fertilizerModeratecrop);
        fertilizer.setRecommend_data(recommendData);
        fertilizerMapper.insertfertilizer(fertilizer);
    }


    public void deleterFertilizerById(int id) {
        fertilizerMapper.deleterFertilizerById(id);
    }
}
