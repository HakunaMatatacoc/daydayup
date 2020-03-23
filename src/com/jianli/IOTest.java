package com.jianli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTest {
    public static void main(String[] args) throws IOException {
//        char c;
//        String str;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter characters: 'q' to quit!");
//        System.out.println("Enter lines of text.Enter 'end' to quit!");
//        //读取字符
//        do{
//            c = (char)br.read();
//            System.out.println(c);
//        }while(c != 'q');

//        do{
//            str = br.readLine();
//            //str指的是一个字符串，结束必须是单独的"end",如果一个字符串包含end,依然会输出
//            System.out.println(str);
//        }while(!str.equals("end"));


        //write
        int a;
        a = 'A';
        System.out.write(a);
        System.out.write('\n');
    }
}
