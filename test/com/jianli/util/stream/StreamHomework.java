package com.jianli.util.stream;

import com.jianli.util.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamHomework {

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
        1.给定一个数字列表,如何返回一个由每个数的平方构成的列表呢?
        给定[1,2,3,4,5],应该返回[1,4,9,16,25]
     */
    @Test
    public void test01(){
        Integer[] nums = new Integer[]{1,2,3,4,5};

        Arrays.stream(nums)
                .map(x -> x * x)
                .forEach(System.out::println);
    }

    /*
        2.怎样用map和reduce方法数一数流中有多少Employee呢？
     */
    @Test
    public void test02(){
        Optional<Integer> count = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }

}
