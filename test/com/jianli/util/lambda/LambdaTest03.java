package com.jianli.util.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaTest03 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三1",18,9999.95),
            new Employee("张三2",19,9999.96),
            new Employee("张三3",20,9999.97),
            new Employee("张三4",36,9999.98),
            new Employee("张三5",45,9999.99)
    );

    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (Employee emp : employees){
            System.out.println(emp.getName() + "," + emp.getAge());
        }
    }

    @Test
    public void test02(){
        String trimStr = strHandler("\t\t\t 找到适合自己的方式   ",str -> str.trim());
        System.out.println(trimStr);

        String upper = strHandler("abcdef",str -> str.toUpperCase());
        System.out.println(upper);

        String newStr = strHandler("找到适合自己的方式",str -> str.substring(2,9));
        System.out.println(newStr);
    }

    //需求:用于处理字符串
    public String strHandler(String str,MyFunction2 mf){
        return mf.getValue(str);
    }

    @Test
    public void test03(){
        op(100L,200L,(x,y) -> x + y);

        op(100L,200L,(x,y) -> x * y);
    }

    //需求:对于两个Long型数据进行处理
    public void op(Long l1,Long l2,MyFunction03<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }
}

