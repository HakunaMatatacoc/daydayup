package com.jianli;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionTest02 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Field f = String.class.getDeclaredField("value");
//        System.out.println(f.getName());
//        System.out.println(f.getType());
//        int m = f.getModifiers();
//        System.out.println(Modifier.isFinal(m));
//        System.out.println(Modifier.isAbstract(m));
//        System.out.println(Modifier.isPrivate(m));
//        System.out.println(Modifier.isProtected(m));
//        System.out.println(Modifier.isPublic(m));
//        System.out.println(Modifier.isStatic(m));

        Person1 p = new Person1("Nezuko");
        System.out.println(p.getName());
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        f.set(p,"Jim");
        System.out.println(p.getName());
    }
}

class Person1{
    private String name;

    public Person1(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
