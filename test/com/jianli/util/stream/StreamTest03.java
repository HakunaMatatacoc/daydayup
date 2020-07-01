package com.jianli.util.stream;

import com.jianli.util.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest03 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三1",18,9999.95, Employee.Status.FREE),
            new Employee("张三2",19,9999.96, Employee.Status.BUSY),
            new Employee("张三3",11,9999.97, Employee.Status.VOCATION),
            new Employee("张三4",36,9999.98, Employee.Status.BUSY),
            new Employee("张三5",45,10000, Employee.Status.FREE),
            new Employee("张三6",45,20000, Employee.Status.VOCATION),
            new Employee("张三7",45,30000, Employee.Status.FREE),
            new Employee("张三8",45,40000, Employee.Status.BUSY)
    );

    /*
        收集
        collect--将流转换为其他形式,接收一个Collector接口的实现,用于给Stream中元素做汇总的方法
     */

    @Test
    public void test08(){
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));

        System.out.println(str);
    }

    @Test
    public void test07(){
        DoubleSummaryStatistics dss = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getCount());
    }

    //分区
    @Test
    public void test06(){
        Map<Boolean,List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));

        System.out.println(map);
    }

    //多级分组
    @Test
    public void test05(){
        Map<Employee.Status,Map<String,List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy(e ->{
                    if(e.getAge() <= 35){
                        return "青年";
                    }else if(e.getAge() <= 50){
                        return "中年";
                    }else {
                        return "老年";
                    }
                })));

        System.out.println(map);
    }

    //分组
    @Test
    public void test04(){
        Map<Employee.Status,List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        Iterator<Map.Entry<Employee.Status,List<Employee>>> entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Employee.Status,List<Employee>> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ",Value = " +entry.getValue());
        }

    }

    @Test
    public void test03(){
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("-----------------");

        //平均值
        Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(avg);

        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println(sum);

        //最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary())));

        System.out.println(max.get());

        //最小值
        Optional<Employee> min = employees.stream()
                .collect(Collectors.minBy((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary())));

        System.out.println(min.get());
    }

    @Test
    public void test02(){
        List<String> list = employees.stream()
                .map(Employee::getName).collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("-----------------");

        Set<String> set =  employees.stream()
                .map(Employee::getName).collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("-----------------");

        HashSet<String> hs = employees.stream()
                .map(Employee::getName).collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    /*
        归约
        reduce(T identity,BinaryOperator) / reduce(BinaryOperator) --可以将流中元素反复结合起来,得到一个值。返回T
        reduce(BinaryOperator b) --可以将流中元素反复结合起来,得到一个值,返回Optional<T>
     */
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //identity:标识累积函数的标识值
        Integer sum = list.stream()
                .reduce(0,(x,y) -> x + y);
        System.out.println(sum);

        System.out.println("---------------");

        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());
    }
}
