package com.template;

public class Browser extends SoftWare {
    @Override
    void initialize() {
        System.out.println("Browser initialized!");
    }

    @Override
    void start() {
        System.out.println("Browser started!");
    }

    @Override
    void end() {
        System.out.println("Browser finished!");
    }
}
