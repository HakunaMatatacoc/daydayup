package com.jianli.util.lambda;

import com.jianli.util.FilterEmployeeByAge;
import com.jianli.util.FilterEmployeeBySalary;
import org.junit.Test;

import java.util.*;

/**
 * Lambda是一个匿名函数,我们可以把Lambda表达式理解为是一段可以传递的代码
 */
public class LambdaTest {

    //原来的匿名内部类
    @Test
    public void test01(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //Lambda 表达式
    @Test
    public void test02(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三1",18,9999.95),
            new Employee("张三2",19,9999.96),
            new Employee("张三3",20,9999.97),
            new Employee("张三4",36,9999.98),
            new Employee("张三5",45,9999.99)
    );

    //需求:获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test03(){
        List<Employee> list = filterEmployees(employees);

        for(Employee employee : list){
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list){
        List<Employee> emps  = new ArrayList<>();
        for (Employee emp: list){
            if(emp.getAge() >= 35){
                emps.add(emp);
            }
        }

        return emps;
    }

    //需求:获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployee2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list){
            if (emp.getSalary() > 5000)
            emps.add(emp);
        }

        return emps;
    }

    //优化方式一:策略模式
    @Test
    public void test04(){
        List<Employee> list = filterEmployee(employees,new FilterEmployeeByAge());

        for (Employee employee: list){
            System.out.println(employee);
        }

        System.out.println("------");
        List<Employee> list1 = filterEmployee(employees,new FilterEmployeeBySalary());

        for (Employee employee : list1){
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();

        for (Employee employee :list){
            if(mp.test(employee)){
                emps.add(employee);
            }
        }

        return emps;
    }

    //优化方式二：匿名内部类
    @Test
    public void test05(){
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee t) {
                return t.getSalary() >= 5000;
            }
        });

        for (Employee employee : list){
            System.out.println(employee);
        }
    }

    //优化方式三:Lambda表达式
    @Test
    public void test06(){
        List<Employee> list = filterEmployee(employees,(e) ->e.getSalary() >= 5000);
        list.forEach(System.out::println);//遍历集合
    }

    //优化方式四:
    @Test
    public void test07(){
        employees.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("---------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
