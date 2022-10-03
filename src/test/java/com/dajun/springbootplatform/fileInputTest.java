package com.dajun.springbootplatform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.dajun.springbootplatform.application.staticVariety.filePath;
import static com.dajun.springbootplatform.application.staticVariety.imgPath;

public class fileInputTest {
    public static void main(String[] args) throws IOException {
        String str = "这是一个测试";
        String path = imgPath+"\\test2";

//        File directory = new File("");//参数为空
//        String courseFile = directory.getCanonicalPath() ;
//        System.out.println(courseFile); //D:\Code\springboot-platform

//        System.out.println(System.getProperty("user.dir")); //D:\Code\springboot-platform
        File file = new File(path);
        if (!file.exists())//如果目录不存在
        {
            System.out.println(file.mkdir()?"目录创建成功":"目录创建失败");;//创建目录
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path+"\\text1.txt");
            fileOutputStream.write(str.getBytes());
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
