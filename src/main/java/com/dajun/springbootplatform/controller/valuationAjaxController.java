package com.dajun.springbootplatform.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import javax.imageio.ImageIO;

@RestController
public class valuationAjaxController {

    @PostMapping("/createCertification")
    public void createCertification(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String m1 = request.getParameter("m1");
        String m2 = request.getParameter("m2");
        String m3 = request.getParameter("m3");
        String m4 = request.getParameter("m4")+" %";
        String m5 = request.getParameter("m5")+" %";
        String m6 = request.getParameter("m6")+" %";
        String m7 = request.getParameter("m7")+" %";
        String m8 = request.getParameter("m8");
        Calendar cal=Calendar.getInstance();
        String y=String.valueOf(cal.get(Calendar.YEAR));
        String m=String.valueOf(cal.get(Calendar.MONTH)+1);
        String d=String.valueOf(cal.get(Calendar.DATE));
        String m9 = y+"年"+m+"月"+d+"日";
        String backgroundPath = "C:\\Users\\44887\\Pictures\\水稻\\证书模板.jpg";
        String outPutPath = "C:\\Users\\44887\\Desktop\\AI背景下的农业一体化智能平台\\springboot-platform\\src\\main\\resources\\static\\img\\证书"+m8+".jpg";
        overlapImage(backgroundPath,m1,m2,m3,m4,m5,m6,m7,m8,m9,outPutPath);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(outPutPath);
    }

    public static String overlapImage(String backgroundPath,String message01,String message02,String message03,String message04,String message05,String message06,String message07,String message08,String message09,String outPutPath){
        try {
            //设置图片大小
//            BufferedImage background = resizeImage(562,400, ImageIO.read(new File("这里是背景图片的路径！")));
            BufferedImage background = resizeImage(400,562, ImageIO.read(new File(backgroundPath)));
            //在背景图片中添加入需要写入的信息，例如：扫描下方二维码，欢迎大家添加我的淘宝返利机器人，居家必备，省钱购物专属小秘书！
            //String message = "扫描下方二维码，欢迎大家添加我的淘宝返利机器人，居家必备，省钱购物专属小秘书！";
            Graphics2D g = background.createGraphics();
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString(message01,175 ,273);
            g.setColor(Color.black);
            g.setFont(new Font("微软雅黑",Font.BOLD,12));
            g.drawString(message02,130 ,319);
            g.drawString(message03,290 ,319);
            g.drawString(message04,130 ,357);
            g.drawString(message05,290 ,357);
            g.drawString(message06,130 ,397);
            g.drawString(message07,290 ,397);
            g.drawString(message08,140 ,435);
            g.setFont(new Font("微软雅黑",Font.BOLD,14));
            g.drawString(message09,240 ,470);
//            ImageIO.write(background, "png", new File("这里是一个输出图片的路径"));
            ImageIO.write(background, "png", new File(outPutPath));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi){
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }
}
