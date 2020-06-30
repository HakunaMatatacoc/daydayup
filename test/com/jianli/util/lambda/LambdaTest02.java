package com.jianli.util.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 一.Lambda表达式的基础语法:Java8引入了一个新的操作符号"->" 该操作符为箭头操作符或Lambda操作符
 *                        箭头操作符Lambda表达式拆分成两部分
 *
 *  左侧:对应Lambda表达式的参数列表
 *  右侧:对应Lambda表达式所需要执行的功能,即Lambda体
 *
 *  语法格式一:无参数,无返回值
 *      () -> System.out.println("Hello Lambda!")
 *
 *  语法格式二:有一个参数,并且无返回值
 *
 *  语法格式三:若只有一个参数,小括号可以省略不写
 *
 *  语法格式四:有两个以上的参数,有返回值,并且Lambda体中有多条语句
 *      Compare<Integer> com = (x,y) ->{
 *          System.out.println("函数式接口");
 *          return Integer.compare(x,y)
 *      };
 *
 *  语法格式五:若Lambda体中只有一条语句,return和{}都可以省略不写
 *
 *  语法格式六:Lambda表达式的参数列表的数据类型可以省略不写,因为JVM编译器可以通过上下文推断出数据类型,即"类型推断"
 *          (Integer x,Integer y) -> Integer.compare(x,y);
 *
 *   口诀：左测遇一括号省，左侧推断类型省
 *
 * 二.Lambda表达式需要"函数式接口"的支持
 *    函数式接口:接口中只有一个抽象方法的接口,称为函数式接口,可以使用@FunctionalInterface修饰
 *              可以检查是否是函数式接口
 */
public class LambdaTest02 {

    @Test
    public void test01(){
        int num= 0;//jdk1.7以前必须是final
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        r.run();

        System.out.println("----------");

        Runnable r1 = () -> System.out.println("Hello Lambda!" + num);
        r1.run();
    }

    @Test
    public void test02(){
        //一个参数的话小括号可以不写
        Consumer<String> com = (x) -> System.out.println(x);
        com.accept("Newbie！");
    }

    @Test
    public void test03(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }

    @Test
    public void test04(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
    }

    @Test
    public void test05(){
//        String[] strs;
//        strs = {"aaa","bbb","ccc"}
//        以上编译不通过
        List<String> list = new ArrayList<>();//后面尖括号没有写String，可以自动推断
        show(new HashMap<>());
    }

    public void show(Map<String,Integer> map){

    }

    //需求:对一个数进行运算
    @Test
    public void test06(){
        Integer num = operation(100,x -> x * x);
        System.out.println(num);

        System.out.println(operation(200,y -> y + 200));

    }

    public Integer operation(Integer num,MyFunction mf){
        return mf.getValue(num);
    }

}
