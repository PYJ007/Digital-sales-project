package com.dajun.springbootplatform.controller;

import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.indexInfo;
import com.dajun.springbootplatform.repository.indexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Abandon_loginController {

    @Autowired
    private com.dajun.springbootplatform.repository.userRepository userRepository;

    @Autowired
    private indexRepository indexRepository;

//    request的请求测试      ->   空指针异常
//    @GetMapping("/client")
//    public List<String> clientDetails(){
//        List<String> list = new ArrayList<>();
//        Connector connector = new Connector();
//        Request request = new Request(connector);
//        list.add(request.getHeader("host"));
//        return list;
//    }

//    测试内容
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userRepository.findAll();
    }//给出的就是json格式？

    @GetMapping("/findById/{account}")
    public User findById(@PathVariable("account") String id){
        return userRepository.findByAccount(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody User user) { //把前端给来的数据转换成java对象
        userRepository.save(user);
    }

    @PutMapping("/upDate")
    public void upDate(@RequestBody User user){
        userRepository.upDate(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/findAllInfo")
    public List<indexInfo> findAllInfo(){
        return indexRepository.findAllInfo();
    }
}
