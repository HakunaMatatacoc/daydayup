package com.jianli.util.stream;

import com.jianli.util.lambda.Employee;
import org.junit.Test;
import org.springframework.util.SocketUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一.Stream的三个操作步骤:
 *
 * 1.创建Stream
 *
 * 2.中间操作
 *
 * 3.终止操作(终端操作)
 */
public class StreamTest01 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三1",18,9999.95),
            new Employee("张三2",19,9999.96),
            new Employee("张三3",20,9999.97),
            new Employee("张三4",36,9999.98),
            new Employee("张三5",45,9999.99)
    );

    //1.创建Stream
    @Test
    public void test01(){
        //1.可以通过Collection系列集合提供的stream()或parallelStream(第一个是串行流,第二个是并行流)
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过Stream类的静态方法of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4.创建无限流
        //迭代
        //按照一元运算的方式产生迭代的无限流
        Stream<Integer> stream4 = Stream.iterate(0,x -> x +2);
//        stream4.forEach(System.out::println);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }

    //中间操作
    /*
        筛选与切片
        filter:接收Lambda,从流中排除某些元素
        limit:截断流,使元素不超过给定数量
        skip(n):跳过元素,返回一个扔掉了前n个元素的流。若流中元素不足n个,则返回一个空流。与limit(n)互补
        distinct:筛选,通过流所生成元素的hashCode()和equals()去除重复元素
     */
    @Test
    public void test02(){
        Stream<Employee> stream = employees.stream()
                                .filter(e -> {
                                    System.out.println("Stream API的中间操作");
                                    return e.getAge() > 35;
                                });
        //中间操作没有结果,须有终止操作
        stream.forEach(System.out::println);
    }
}
