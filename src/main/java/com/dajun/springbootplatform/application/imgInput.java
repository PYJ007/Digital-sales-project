package com.dajun.springbootplatform.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.dajun.springbootplatform.application.staticVariety.imgPath;

public class imgInput {
    /**
    * @param    specialistPhone      专家的名称
    * @param    fileName            文件名，必须携带后缀
    * @param    img                要写入的文件
     * 这里给出专家名是为了创建目录用的，存在images下的<专家名>文件夹中，文件名建议用种子名称作为命名
    * */

    public void writeImg(String specialistPhone, String fileName, MultipartFile img) throws Exception {

        String localPath = "/root/photo";
        //2获得文件名字
        String realPath = localPath + "/" + fileName;
        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            img.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String path = "/home/dajun/image";
//        File file = new File(path);
//        if (!file.exists())//如果目录不存在
//        {
//            if (!file.mkdir())throw new Exception("目录创建失败") ;//创建目录,如果失败则抛出异常
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+fileName);
//        fileOutputStream.write(img.getBytes());//写入
//        fileOutputStream.close();//关闭
    }
}
