package com.proxy;

public class ProxyPrinter implements Printer {
     private ConsolePrinter consolePrinter;
     private String filename;

    public ProxyPrinter(String filename) {
        this.filename = filename;
    }

    @Override
    public void print() {
        if (consolePrinter == null){
            consolePrinter = new ConsolePrinter(filename);
        }
        consolePrinter.print();
    }
}
