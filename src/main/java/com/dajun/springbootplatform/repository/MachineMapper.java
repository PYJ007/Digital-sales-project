package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Machine;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MachineMapper {

    List<Machine> selectnongjilistbynull();

    void insertagrimechine(String machineName, String machineFunction, String machineManufacturer, String machinePhone, String machinePrice, String machineAddress, String machineType, String recommendData, String machineModeratecrop);

    int deletemachineinfo(int machineId);

    int deletesomemachineinfo(HashMap<String, List<String>> map);
}
