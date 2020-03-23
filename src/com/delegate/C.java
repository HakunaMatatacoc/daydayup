package com.delegate;

public class C implements I {
    //delegation
    I i = new A();

    @Override
    public void f() {
        i.f();
    }

    @Override
    public void g() {
        i.g();
    }

    public void toA(){
        i = new A();
    }

    public void toB(){
        i = new B();
    }
}
