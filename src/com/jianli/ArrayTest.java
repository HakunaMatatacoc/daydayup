package com.jianli;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        double[] myList =  {1.1,2.2,3.3};

        for (double element:
             myList) {
            System.out.println(element);
        }
        //调用以数组为参数的方法
        printArray(new int[]{1,2,3,4,5});

        //测试Arrays.equals()
        System.out.println();
        int[] a = {1,2,3,4,5};
        int[] b = {1,2,3,4,5};
        System.out.println(Arrays.equals(a,b));

        //fill下标2-4，包括下标2，不包括下标4
        Arrays.fill(a,2,4,6);
        for (int element:
             a) {
            System.out.print(element + " ");
        }

        //测试Arrays.sort()
        System.out.println();
        double[] dsort = {1.1,2.2,4.4,6.6,1.3};
        Arrays.sort(dsort);
        for (double element:
             dsort) {
            System.out.print(element + " ");
        }

        //测试Arrays.binarySearch()
        System.out.println("搜索元素的下标是:");
        int[] binary = {1,2,3,4,5,6,7,8,9,10,13};
        System.out.println(Arrays.binarySearch(binary,13));
    }

    public static void printArray(int[] array){
        for(int i = 0;i < array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
