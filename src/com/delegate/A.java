package com.delegate;

public class A implements I {
    @Override
    public void f() {
        System.out.println("A doing f()");
    }

    @Override
    public void g() {
        System.out.println("A doing g()");
    }
}
