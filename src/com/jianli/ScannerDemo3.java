package com.jianli;

import java.util.Scanner;

public class ScannerDemo3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double x = 0.0d;
        double sum = 0.0d;
        int m = 0;

        while(scan.hasNextDouble()){
            x = scan.nextDouble();
            m = m + 1;

            sum = sum +x;
        }
        System.out.println(m + "个数的和为:" +sum);
        System.out.println(m + "个数的平均值是:" + sum/m);
    }
}
