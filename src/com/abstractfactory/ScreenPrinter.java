package com.abstractfactory;

public class ScreenPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("screen");
    }
}
