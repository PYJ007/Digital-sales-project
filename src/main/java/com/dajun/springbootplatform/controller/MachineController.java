package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.Machine;
import com.dajun.springbootplatform.repository.MachineMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MachineController {

    @Resource
    private MachineMapper machineMapper;

    @RequestMapping("/recommendNJ")
    public String getRecommendNJ(Model model){
        List<Machine> machines = machineMapper.selectnongjilistbynull();
        model.addAttribute("machines",machines);
        return "recommend/recommendNJ";
    }

    //专家插入农机信息
    @PostMapping(value = "/agrimachineinfoinsert")
    public String machineInsert(@RequestParam String machineName,
                                @RequestParam String machineFunction,
                                @RequestParam String machineManufacturer,
                                @RequestParam String machinePhone,
                                @RequestParam String machinePrice,
                                @RequestParam String machineAddress,
                                @RequestParam String machineType,
                                @RequestParam String recommendData,
                                @RequestParam String machineModeratecrop
    ) {
        machineMapper.insertagrimechine(machineName,
                machineFunction,
                machineManufacturer,
                machinePhone,
                machinePrice,
                machineAddress,
                machineType,
                recommendData,
                machineModeratecrop);
        return "redirect:/recommendNJ";
    }

    @PostMapping("/deleteMachine")
    public String deleteMachine(@RequestParam Integer id){
        machineMapper.deletemachineinfo(id);
        return "redirect:/recommendNJ";
    }

}

