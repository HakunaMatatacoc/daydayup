package com.jianli;

public class ExceptionTest02 {
    public static void main(String[] args) {
        String a = "12";
        String b = "x9";
        // TODO: 捕获异常并处理
        int c = 0;
        int d = 0;
        try {
            c = stringToInt(a);
            d = stringToInt(b);
            System.out.println(c * d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int stringToInt(String s) {
        return Integer.parseInt(s);
    }
}
