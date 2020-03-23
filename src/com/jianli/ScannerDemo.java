package com.jianli;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        //从键盘接收数据
        Scanner scan = new Scanner(System.in);
        //用next方式接收字符串,遇到空格就不接收了
//        if (scan.hasNext()){
//            String str = scan.next();
//            System.out.println("输入的数据为:" + str);
//        }
        if(scan.hasNext()){
            String str = scan.nextLine();
            System.out.println("输入的数据为:" + str);
        }
    }
}
