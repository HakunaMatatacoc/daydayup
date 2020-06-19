package com.jianli;

public class ReflectionTest04 {
    public static void main(String[] args) {
        //获取父类的class
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);
        Class o = n.getSuperclass();
        System.out.println(o);
        System.out.println(o.getSuperclass());

        // 获取Interface
        Class[] is = i.getInterfaces();
        for(Class i1 : is){
            System.out.println(i1);
        }

        //对所有interface的Class调用getSuperclass()返回的是null，获取接口的父接口要用getInterfaces()
        Class in = Integer.class;
        Class[] in1 = in.getInterfaces();
        for (Class in2 : in1){
            System.out.println(in2);
            for (Class in3 : in2.getInterfaces()){
                System.out.println(in3);
            }
        }

        //通过Class对象的isAssignableFrom方法可以判断一个向上转型是否可以实现
        System.out.println(Integer.class.isAssignableFrom(Integer.class));
        System.out.println(Number.class.isAssignableFrom(Integer.class));
        System.out.println(Object.class.isAssignableFrom(Integer.class));
        System.out.println(Integer.class.isAssignableFrom(Number.class));
    }
}
