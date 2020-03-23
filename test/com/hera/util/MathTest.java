package com.hera.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * 关于MathTest，有六点需要注意
 * 1.导入了org.junit.Test和org.junit.Assert.*这两个包，注意后者是静态导入import static
 *     ps：Java静态导入就是通过给导入包名中添加一个 static 关键字，从而直接通过方法名使用方法。
 *     这样的好处是无须使用类名调用，或者新建一个对象来调用其中的方法。使用静态导入犹如在该类中创建静态方法一样便利;
 * 2.testFactorial是在要测试的方法名Factorial前加个test(这也是个好习惯)
 * 3.所有测试方法返回类型必须为void且无参数
 * 4.一个测试方法之所以是个测试方法是因为@Test这个注解
 * 5.assertEquals的作用是判断两个参数是否相等，例子中120是预期结果，new Math().factorial(5)是实际结果。
 * 但是通常不应该只比较一个值，要测试多几个特殊值。特别是临界值。例如Math().factorial(0)和Math.factorial(-1)等
 * 6.assertEquals除了比较两个int，还存在了好多次可以比较很多种类型的参数。而且JUnit4包含了一堆assertXX方法，assertEquals
 * 只是其中之一,这些assertXX统称为断言。junit-4.12-javadoc.jar这个文档解压后打开index.html还有一堆断言.
 */
public class MathTest {

    @Before
    public void setUp() throws Exception{

    }

    @Test
    public void testFactorial() throws Exception {
        assertEquals(120,new Math().factorial(5));
    }

    @Test (expected = Exception.class)
    public void testFactorialException() throws Exception{
        new Math().factorial(-1);
        fail("factorial参数为负数没有抛出异常");
    }

    @Test(timeout = 2000)
    public void testSort() throws Exception {
        int[] arr = new int[50000]; //数组长度为50000
        int arrLength = arr.length;
        //随机生成数组元素
        Random r = new Random();
        for (int i = 0; i < arrLength; i++) {
            arr[i] = r.nextInt(arrLength);
        }

        new Math().sort(arr);
    }

    @Test(timeout = 2000)
    public void testquickSort() throws Exception {
        int[] arr = new int[50000]; //数组长度为50000
        int arrLength = arr.length;
        //随机生成数组元素
        Random r = new Random();
        for (int i = 0; i < arrLength; i++) {
            arr[i] = r.nextInt(arrLength);
        }

        new Math().quickSort(arr);
    }
}