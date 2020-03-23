package com.jianli;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest2 {
    public static void main(String[] args)throws IOException {
        File file = new File("Hello.txt");
        //创建文件
        file.createNewFile();
        //create a FileWriter Object
        FileWriter writer = new FileWriter(file);
        //向文件写入内容
        writer.write("Dance like nobody is watching , sing like nobody is listening!");
        System.out.println();
        writer.flush();
        writer.close();
        //创建 FileReader 对象
        FileReader fr = new FileReader(file);
        char[] c = new char[70];
        fr.read(c);//读取数组中的内容
        for(char a : c)
            System.out.print(a);
        fr.close();
    }
}
