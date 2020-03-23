package com.strategy;

public class MathContext {
    public MathAlgorithm algorithm;

    public MathContext(MathAlgorithm stategy){
        this.algorithm = stategy;
    }

    public int execute(int num1,int num2){
        return algorithm.calculate(num1,num2);
    }

}
