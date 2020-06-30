package com.jianli.util;

public class InnerClassTest {
    public int Age;
    public void say(){
        String name = "Nezuko";
        class InnerClass{
            public void hello(){
                System.out.println(name + ",你好");
            }
        }
        InnerClass tClass = new InnerClass();
        tClass.hello();
        System.out.println("这个方法中包括局部内部类");
    }

    public static void main(String[] args) {
        InnerClassTest t = new InnerClassTest();
        t.say();
    }
}
