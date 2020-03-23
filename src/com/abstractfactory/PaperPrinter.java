package com.abstractfactory;

public class PaperPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("paper");
    }
}
