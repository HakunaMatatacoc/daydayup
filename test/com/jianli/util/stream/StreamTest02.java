package com.jianli.util.stream;

import com.jianli.util.lambda.Employee;
import org.apache.commons.collections.iterators.EmptyListIterator;
import org.junit.Test;

import javax.print.attribute.standard.MediaSize;
import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 终止操作
 */
public class StreamTest02 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三1",18,9999.95, Employee.Status.FREE),
            new Employee("张三2",19,9999.96, Employee.Status.BUSY),
            new Employee("张三3",11,9999.97, Employee.Status.VOCATION),
            new Employee("张三4",36,9999.98, Employee.Status.BUSY),
            new Employee("张三5",45,9999.99, Employee.Status.FREE),
            new Employee("张三5",45,9999.99, Employee.Status.VOCATION),
            new Employee("张三5",45,9999.99, Employee.Status.FREE),
            new Employee("张三5",45,9999.99, Employee.Status.BUSY)
    );
    /*
        查找与匹配
        allMatch--检查是否匹配所有元素
        anyMatch--检查是否至少匹配一个元素
        noneMatch--检查是否没有匹配所有元素
        findFirst--返回第一个元素
        findAny--返回当前流中的任意元素
        count--返回流中元素的总个数
        max--返回流中最大值
        min--返回流中最小值
     */

    @Test
    public void test03(){
        Long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> op1 = employees.stream()
                .max((e1,e2) ->Double.compare(e1.getSalary(),e2.getSalary()));
        System.out.println(op1.get());

        Optional<Double> op2 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op2.get());

        Optional<Employee> op3 = employees.stream()
                .min((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()));
        System.out.println(op3.get().getName() + ":" + op3.get().getSalary());
    }

    @Test
    public void test01(){
        boolean b1 = employees.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        Optional<Employee> op = employees.stream()
                .sorted((e1,e2) ->Double.compare(e1.getSalary(),e2.getSalary()))
                .findFirst();
        System.out.println(op.get());
        //op.orElse(other)可以对空值进行另外的赋值处理，相当于SQL中的 if null

        Optional<Employee> op2 = employees.stream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(op2.get());

    }

}
