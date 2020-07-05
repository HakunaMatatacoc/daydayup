package com.jianli.util.optional;

import com.jianli.util.lambda.Employee;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OptionalTest {
    /*
        Optional 容器类的常用方法:
        Optional.of(T t) : 创建一个Optional实例
        Optional.empty() : 创建一个空的Optional实例
        Optional.ofNullable(T t) : 若t不为null,创建Optional实例,否则创建空实例
        isPresent() : 判断是否包含值
        orElse(T t) : 如果调用对象包含值,返回该值,否则返回t
        orElseGet(Supplier s) : 如果调用对象包含值,否则返回s获取的值
        map(Function f) : 如果有值对其处理,并返回处理后的Optional,否则返回Optional.empty()
        flatMap(Function mapper) : 与map类似,要求返回值必须是Optional
     */
    @Test
    public void test5(){
        Optional<Employee> op = Optional.ofNullable(new Employee("Nezuko",18,3000, Employee.Status.FREE));

//        Optional<String> str = op.map(e -> e.getName());
////        System.out.println(str.get());

        Optional<String> str2 = op.flatMap(e -> Optional.of(e.getName()));//进一步防止空指针异常
        System.out.println(str2);
    }

    @Test
    public void test04(){
        Optional<Employee> op = Optional.ofNullable(null);

        Employee emp = op.orElseGet(() -> new Employee());
        System.out.println(emp);
    }

    @Test
    public void test03(){
        Optional<Employee> op = Optional.ofNullable(null);

        //有值就获取,没值就什么都不做
        if(op.isPresent())
            System.out.println(op.get());

        Employee emp = op.orElse(new Employee("Nezuko",18,3000, Employee.Status.VOCATION));
        System.out.println(emp);
    }

    @Test
    public void test02(){
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test01(){
        Optional<Employee> op = Optional.of(null);//可以捕捉到空指针异常

        Employee emp = op.get();
        System.out.println(emp);
    }

    //例题
    @Test
    public void test06(){
//        Man man = new Man();
//
//        String n = getGodnessName(man);
//        System.out.println(n);
        Optional<Godness> gn = Optional.ofNullable(new Godness("Michael"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
        String str = getGodnessName2(op);
        System.out.println(str);
    }

    //需求:获取一个男人心中女神的名字
    public String getGodnessName(Man man){
        if(man != null){
            Godness gn = man.getGodness();

            if(gn != null){
                return gn.getName();
            }
        }

        return "Nezuko";
    }

    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("Nezuko"))
                .getName();
    }
}
