package com.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        MathContext mathContext = new MathContext(new MathAdd());
        System.out.println("10 + 5 = " + mathContext.execute(10,5));

        MathContext mathContext1 = new MathContext(new MathSubstract());
        System.out.println("10 - 5 = " + mathContext1.execute(10,5));

        MathContext mathContext2 = new MathContext(new MathMultiply());
        System.out.println("10 * 5 = " + mathContext2.execute(10,5));
    }
}
