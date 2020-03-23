package com.strategy;

public class MathAdd implements MathAlgorithm {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
