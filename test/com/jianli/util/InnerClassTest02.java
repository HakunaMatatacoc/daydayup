package com.jianli.util;

public class InnerClassTest02 {
    public static void main(String[] args) {
        MyInterface mi = new MyInterface() {
            @Override
            public void say() {
                System.out.println("实现接口");
            }
        };

        MyClass mc = new MyClass() {
            @Override
            public void hello() {
                System.out.println(name + "匿名子类");
            }
        };
        mc.name = "Nezuko";
        mc.hello();

        //多态：父类类型的变量指向子类类型的对象，调用方法的时候，是调用子类的实现(子类重写/实现)
    }
}

interface MyInterface {
    void say();
}

class MyClass {
    public int age;
    public String name;

    public void hello() {
        System.out.println("父类方法");
    }
}
