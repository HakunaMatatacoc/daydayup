package com.jianli;

import java.math.BigInteger;

/**
 * BigInteger是不可变类
 * pow(int exponent)
 * 指数
 */
public class BigIntegerTest {
    public static BigInteger b = new BigInteger("99999999").pow(2);

    public static void main(String[] args) {
        System.out.println(b.longValueExact());
    }
}
