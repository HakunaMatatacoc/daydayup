package com.jianli.util.stream;

import com.jianli.util.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
            new Employee("张三3",11,9999.97),
            new Employee("张三4",36,9999.98),
            new Employee("张三5",45,9999.99),
            new Employee("张三5",45,9999.99),
            new Employee("张三5",45,9999.99),
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
        排序
        sorted() --自然排序
        sorted(Comparator com) --定制排序(Comparator)
     */
    @Test
    public void test08(){
        List<String> list = Arrays.asList("ccc","aaa","bbb","ddd","eee");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("-------------------");

        employees.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge() == e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return new Integer(e1.getAge()).compareTo(new Integer(e2.getAge()));
                    }
                }).forEach(System.out::println);
    }

    /*
        映射
        map--接收Lambda,将元素转换成其他形式或提取信息。接收一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素。
        flatMap--接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
     */

    @Test
    public void test07(){
        List<String> list  = Arrays.asList("aaa","bbb","ccc","dddd");

        List list2 = new ArrayList();

        list2.add(11);
        list2.add(22);
        list2.add(list);

        System.out.println(list2);

        list2.addAll(list);
        System.out.println(list2);
    }

    @Test
    public void test06(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");

        list.stream()
                .map(str -> str.toUpperCase())
                    .forEach(System.out::println);

        System.out.println("------------");

        employees.stream()
                    .map(Employee::getName)
                    .forEach(System.out::println);

        System.out.println("------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(StreamTest01::filterCharacter);//{{a,a,a},{b,b,b},{c,c,c},{d,d,d}}
        stream.forEach(sm -> {
            sm.forEach(System.out::println);
        });

        System.out.println("------------");

        Stream<Character> sm = list.stream().flatMap(StreamTest01::filterCharacter);//{a,a,a,b,b,b,c,c,c,d,d,d}
        sm.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        //add(Object obj)如果传过来的是一个集合,则将整个集合添加到当前集合中
        //addAll(Collection coll)如果传过来的是一个集合,则将整个集合中的元素添加到当前集合中
        List<Character> list = new ArrayList<>();

        for(Character ch : str.toCharArray()){
            list.add(ch);
        }

        return list.stream();
    }

    /*
        筛选与切片
        filter:接收Lambda,从流中排除某些元素
        limit:截断流,使元素不超过给定数量
        skip(n):跳过元素,返回一个扔掉了前n个元素的流。若流中元素不足n个,则返回一个空流。与limit(n)互补
        distinct:筛选,通过流所生成元素的hashCode()和equals()去除重复元素
     */

    @Test
    public void test05(){
        employees.stream()
                    .filter(e -> e.getSalary() > 5000)
                        .distinct()
                            .forEach(System.out::println);
    }

    @Test
    public void test04(){
        employees.stream()
                    .filter(e -> {
                        System.out.println("短路");//迭代两次,找到需要的数据之后不再继续迭代
                        return e.getSalary() > 5000;
                    })
                    .skip(2L)
                    .limit(2)
                    .forEach(System.out::println);
    }

    //内部迭代:迭代操作由Stream API完成
    @Test
    public void test02(){
        //中间操作:不会执行任何操作
        Stream<Employee> stream = employees.stream()
                                .filter(e -> {
                                    System.out.println("Stream API的中间操作");
                                    return e.getAge() > 35;
                                });
        //中间操作没有结果,须有终止操作
        //终止操作:一次性执行全部内容,即"惰性求值"
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test03(){
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
