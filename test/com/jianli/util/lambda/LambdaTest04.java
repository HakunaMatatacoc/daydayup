package com.jianli.util.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 *      void accept(T t);
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Function<T,R> : 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> :断言型接口
 *      boolean test(T t);
 */
public class LambdaTest04 {

    //Predicate<T> 断言型接口:
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","Nezuko","Lambda","HakunaMatata");
        List<String> strList = filterStr(list,pre -> pre.length() > 3);

        for(String str : strList){
            System.out.println(str);
        }
    }

    //需求:将满足条件的字符串放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();

        for(String str :list){
            if(pre.test(str)){
                strList.add(str);
            }
        }

        return strList;
    }

    //Function<T,R> 函数型接口:
    @Test
    public void test3(){
        String newStr = strHandler("\t\t\t B站抽奖   ",str -> str.trim());
        System.out.println(newStr);

        String newStr2 = strHandler("B站抽奖28一次,欧气",str -> str.substring(9,11));
        System.out.println(newStr2);

        String newStr3 = strHandler("abcdrfg",str -> str.toUpperCase());
        System.out.println(newStr3);
    }

    //需求:用于处理字符串
    public String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    //Supplier<T> 供给型接口:
    @Test
    public void test2(){
       List<Integer> numlist =  getNumList(10,() -> (int)(Math.random() * 100));

       for(Integer num : numlist){
           System.out.println(num);
       }
    }

    //需求:产生指定个数的整数,并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();

        for(int i = 0;i < num;i++){
            Integer n = sup.get();
            list.add(n);
        }

        return list;
    }

    //Comsumer<T> 消费型接口:
    @Test
    public void test1(){
        happy(10000,m -> System.out.println("B站抽奖消费:" + m + "¥"));
    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }
}
