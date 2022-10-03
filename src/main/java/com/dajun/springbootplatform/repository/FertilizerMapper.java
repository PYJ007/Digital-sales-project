package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Fertilizer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FertilizerMapper{

    List<Fertilizer> selecthuafeilistbynull();

    void insertfertilizer(Fertilizer fertilizer);

    //通过ID删除一个化肥
    void deleterFertilizerById(int id);


}
