package com.jianli;

import java.io.*;

public class FileStringTest2 {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/lijian/Documents/text.txt");
        FileOutputStream fos = new FileOutputStream("/Users/lijian/Documents/text.txt");
        OutputStreamWriter writer = new OutputStreamWriter(fos,"UTF-8");
        //写入到缓冲区
        writer.append("中文输入");
        writer.append("\r\n");
        writer.append("English");
        writer.close();

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fis,"UTF-8");
        StringBuffer sb = new StringBuffer();
        while(reader.ready()){
            sb.append((char) reader.read());
        }
        System.out.println(sb.toString());
        fis.close();
        reader.close();
    }
}
