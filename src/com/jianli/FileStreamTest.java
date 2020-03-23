package com.jianli;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileStreamTest {
    public static void main(String[] args) throws IOException{
        byte[] b = {11,22,33,44,55,66};
        OutputStream os = new FileOutputStream("/Users/lijian/Documents/text.txt");
        for(int i = 0;i < b.length;i++){
            //以下代码是二进制写入
            os.write(b[i]);
        }
        os.close();
    }
}
