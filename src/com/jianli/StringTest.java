package com.jianli;


import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;

import java.io.UnsupportedEncodingException;

public class StringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        char[] a = {'h','e','l','l','o'};
        String b = new String(a);
        System.out.println(b);
        String c = "hElLo";

        float floatVar = 1.12f;
        int intVar = 1;
        String stringVar = "hahaha!";

        String fs;
        fs = String.format("The value of the float variable is " +
                "%f, while the value of the integer " +
                "variable is %d, and the string " +
                "is %s", floatVar, intVar, stringVar);
        System.out.println(fs);

        System.out.println(fs.charAt(2));

        System.out.println(b.compareTo(c));
        System.out.println(b.compareTo("hello"));

        System.out.println(b.compareToIgnoreCase(c));

        System.out.println(b.concat("hello"));

        StringBuffer d = new StringBuffer("hello");
        //当且仅当字符串与指定的StringButter有相同顺序的字符时候返回真。
        System.out.println(b.contentEquals(d));

        //static String copyValueOf(char[] data),返回指定数组中表示该字符序列的 String。
        System.out.println(String.copyValueOf(a));
        System.out.println(String.copyValueOf(a,1,3));

        System.out.println(b.endsWith("lo"));
        //与compareTo,compareToIngnoreCase返回值不一样
        System.out.println(b.equalsIgnoreCase(c));

        byte[] e = b.getBytes();
        byte[] f = b.getBytes("gbk");

        System.out.println(e.toString());
        System.out.println(f);

        char[] g = {'h','m','l','l','o'};
        //src:source,dst:destination
        b.getChars(0,2,g,3);
        System.out.println(g);

        System.out.println(b.hashCode());
        //返回指定字符在此字符串中第一次出现处的索引。
        System.out.println(b.indexOf('h'));
    }
}
