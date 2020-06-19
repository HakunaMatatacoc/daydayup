package com.jianli;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ExceptionTest {
    public static void main(String[] args) {
        byte[] bs = toGBK("中文");
        System.out.println(Arrays.toString(bs));
    }
    public static byte[] toGBK(String input) {
        try {
            return input.getBytes("GBK");
        }catch (UnsupportedEncodingException e){
            System.out.println(e);
            return input.getBytes();
        }
    }
}
