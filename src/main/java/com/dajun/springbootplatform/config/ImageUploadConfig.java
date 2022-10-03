package com.dajun.springbootplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageUploadConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        C:\Users\44887\Desktop\AI背景下的农业一体化智能平台
        registry.addResourceHandler("/static/img/**").addResourceLocations("file:C:\\Users\\44887\\Desktop\\AI背景下的农业一体化智能平台\\springboot-platform\\src\\main\\resources\\static\\img\\");
    }
}
