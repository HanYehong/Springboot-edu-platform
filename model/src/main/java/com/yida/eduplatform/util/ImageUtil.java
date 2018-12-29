package com.yida.eduplatform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {


//    public static void main(String[] args) throws IOException  {
//        String imagePath = "C:\\Users\\pc\\Desktop\\446968702368648824.jpg";
//        byte[] bytes = null;
////      将图片转化为二进制流
//        bytes = imageToByte(imagePath);
//
//        for (byte byte1 : bytes) {
//            System.out.print(byte1);
//        }
//
////      将二进制图片转化为字符串
////      String string = new String(bytes);
////      System.out.println(string);
//
//        String path = "C:\\Users\\pc\\Desktop\\446.jpg";
////      将二进制流转化为图片
//        byteToImage(bytes, path);
//
//    }

    public static byte[] imageToByte(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();

        return bytes;
    }
    //输出图像到新的路径
    public static void byteToImage(byte[] bytes,String newPath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(newPath));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

}