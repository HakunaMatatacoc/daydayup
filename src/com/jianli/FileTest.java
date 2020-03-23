package com.jianli;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        String dirname = "/Users/lijian/IdeaProjects";
        File f1 = new File(dirname);
        if (f1.isDirectory()) {
            System.out.println("Directory of" + dirname);
            String[] s = f1.list();
            for (int i = 0; i < s.length; i++) {
                File f = new File(dirname + "/" + s[i]);
                if (f.isDirectory()) {
                    System.out.println(s[i] + " is a Directory");
                } else if (f.isFile()) {
                    System.out.println(s[i] + "is a File");
                } else {
                    System.out.println(s[i] + "is not a directory");
                }
            }
        }
        System.out.println(f1.getName());
        //返回String
        System.out.println(f1.getParent());
        //返回File
        System.out.println(f1.getParentFile());
        System.out.println(f1.getPath());

    }
}
