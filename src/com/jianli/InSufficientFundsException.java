package com.jianli;

public class InSufficientFundsException extends Exception{
    private double amount;

    public InSufficientFundsException(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
