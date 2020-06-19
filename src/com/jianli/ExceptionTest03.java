package com.jianli;

public class ExceptionTest03 {
    public static void main(String[] args) {
        System.out.println(tax(2000, 0.1));
        System.out.println(tax(-200, 0.1));
        System.out.println(tax(2000, -0.1));
    }

    static double tax(int salary, double rate) {
        // TODO: 如果传入的参数为负，则抛出IllegalArgumentException
        if (salary < 0 || rate < 0) {
            throw new IllegalArgumentException("传入值不能为负数");
        } else {
            return salary * rate;
        }
    }
}
