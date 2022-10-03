package com.dajun.springbootplatform.service;

import com.dajun.springbootplatform.entities.Fertilizer;

import java.util.List;


public interface IFertilizerService {

    List<Fertilizer> selectfertilizerList();

    void agriMechineinsert(String fertilizerName, String fertilizerType, String fertilizerN, String fertilizerP, String fertilizerK, String fertilizerOther, String fertilizerInstructions, String fertilizerPrice, String fertilizerManufacturer, String fertilizerPhone, String fertilizerAddress, String fertilizerProductiondate, String fertilizerShelflife, String fertilizerModeratecrop, String recommendData);

    //通过ID删除一个化肥
    void deleterFertilizerById(int id);
}
