package com.jianli.util;

public class VarargsTest {

    public static void main(String... args) {
        int max = max(1,4,6,3,9,100);
        System.out.println("max number: " + max);
    }

    public static int max(int... num){
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < num.length;i++){
            if(num[i] > max){
                max = num[i];
            }
        }
        return max;
    }
}
