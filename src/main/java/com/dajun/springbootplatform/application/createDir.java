package com.dajun.springbootplatform.application;

import java.io.File;

public class createDir {
/*
* @param String  dirName = "D:/work/temp/temp0/temp1";
* 用于创建目录，如果目录已经存在，则不创建
* */
    public static boolean create(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
}
