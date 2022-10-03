package com.dajun.springbootplatform.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

import static com.dajun.springbootplatform.application.staticVariety.filePath;

public class policyInput {
    public void writeFile(String specialistPhone, String fileName, MultipartFile img) throws Exception {
        String path = filePath;
//        + "\\" + specialistPhone;
        File file = new File(path);
        if (!file.exists())//如果目录不存在
        {
            if (!file.mkdir())throw new Exception("目录创建失败") ;//创建目录,如果失败则抛出异常
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path+"\\"+fileName);
        fileOutputStream.write(img.getBytes());//写入
        fileOutputStream.close();//关闭
    }
}
