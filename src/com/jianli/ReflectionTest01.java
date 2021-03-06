package com.jianli;

public class ReflectionTest01 {
    public static void main(String[] args) throws NoSuchFieldException {
        Class stdClass = Student.class;
        System.out.println(stdClass.getField("score"));
        System.out.println(stdClass.getField("name"));
        System.out.println(stdClass.getDeclaredField("grade"));
        System.out.println(stdClass.getDeclaredFields());
    }
}

class Person{
    public String name;
}

class Student extends Person{
    public int score;
    private int grade;
}
