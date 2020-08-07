package com.jianli;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest03 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class stdClass = Student2.class;
        System.out.println(stdClass.getMethod("getScore",String.class));
        System.out.println(stdClass.getMethod("getName"));
        System.out.println(stdClass.getDeclaredMethod("getGrade",int.class));

        Method m = stdClass.getMethod("getScore", String.class);
        System.out.println(m.getName());
        System.out.println(m.getReturnType());
        System.out.println(m.getParameterTypes());
        System.out.println(m.getModifiers());

        //调用单参数方法
        String s = "Hello World";
        Method m1 = String.class.getMethod("substring",int.class);
        String r = (String)m1.invoke(s,6);
        System.out.println(r);

        //调用多参数方法
        Method m2 = String.class.getMethod("substring",int.class,int.class);
        String r2 = (String)m2.invoke(s,6,8);
        System.out.println(r2);

        //调用静态方法
        Method m3 = Integer.class.getMethod("parseInt", String.class);
        Integer n = (Integer)m3.invoke(null,"123456");
        System.out.println(n);

        //调用非public方法
        Person2 p = new Person2();
        Method m4 = p.getClass().getDeclaredMethod("setName",String.class);
        m4.setAccessible(true);
        m4.invoke(p,"Michael");
        System.out.println(p.name);

        //多态:使用反射调用方法时，仍然遵循多态原则
        Method m5 = Person2.class.getMethod("hello");
        m5.invoke(new Student2());

        //调用构造方法
        Constructor cons1 = Integer.class.getConstructor(int.class);
        Integer n1 = (Integer)cons1.newInstance(123);
        System.out.println(n1);

        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer)cons2.newInstance("456");
        System.out.println(n2);
    }
}

class Student2 extends Person2{
    public int getScore(String type){
        return 99;
    }

    private int getGrade(int year){
        return 1;
    }

    @Override
    public void hello() {
        System.out.println("Student:Hello");
    }
}

class Person2{
    String name;

    public String getName(){
        return "Person";
    }

    private void setName(String name){
        this.name = name;
    }

    public void hello(){
        System.out.println("Person:Hello");
    }
}
