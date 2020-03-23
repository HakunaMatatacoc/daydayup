package com.delegate;

public class B implements I {
    @Override
    public void g() {
        System.out.println("B doing g()");
    }

    @Override
    public void f() {
        System.out.println("B doing f()");
    }
}
