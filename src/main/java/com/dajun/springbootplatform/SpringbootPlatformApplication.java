package com.dajun.springbootplatform;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dajun.springbootplatform.repository")
public class SpringbootPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootPlatformApplication.class, args);
    }

}
