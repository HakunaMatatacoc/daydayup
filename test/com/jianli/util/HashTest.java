package com.jianli.util;

public class HashTest {
    private String title;
    private String author;

    public int hashCode(){
        int hash = 37;
        int code = 0;

        code = (title == null ? 0 : title.hashCode());
        hash = hash * 59 +code;

        code = (author == null ? 0 : author.hashCode());
        hash = hash * 59 + code;

        return hash;
    }

    public static void main(String[] args) {
        HashTest hashTest = new HashTest();
        hashTest.title = "Michael.Lee";
        System.out.println(hashTest.hashCode());
        hashTest.author = "jianli";
        System.out.println(hashTest.hashCode());
    }
}
