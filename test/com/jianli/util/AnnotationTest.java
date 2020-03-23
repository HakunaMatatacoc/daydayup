package com.jianli.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * JUnit4为了保证每个测试方法都是单元测试，是独立的互不影响。
 * 所以每个测试方法执行前都会重新实例化测试类
 *
 *  可以看到test1和test2的i都只自增了一次，所以test1的执行不会影响test2，
 *  因为执行test2时又把测试类重新实例化了一遍。如果希望test2的执行受test1的影响,
 *  把int i改为static
 *
 *  @Test：把一个方法标记为测试方法
 *  @Before：每一个测试方法执行前自动调用一次
 *  @After：每一个测试方法执行完自动调用一次
 *  @BeforeClass：所有测试方法执行前执行一次，在测试类还没有实例化就已经被加载，所以用static修饰
 *  @AfterClass：所有测试方法执行完执行一次，在测试类还没有实例化就已经被加载，所以用static修饰
 *  @Ignore：暂不执行该测试方法
 */
public class AnnotationTest {
    public AnnotationTest(){
        System.out.println("构造方法");
    }

    int i = 0;

    @BeforeClass
    public static void setInBeforeClass(){
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("AfterClass");
    }

    @Before
    public void setIn(){
        System.out.println("Before");
    }

    @After
    public void teatDown(){
        System.out.println("After");
    }

    @Test
    public void test1(){
        i++;
        System.out.println("test1" + i);
    }

    @Test
    public void test2(){
        i++;
        System.out.println("test2" + i);
    }

    @Ignore
    public void test3(){
        System.out.println("test3");
    }

}
