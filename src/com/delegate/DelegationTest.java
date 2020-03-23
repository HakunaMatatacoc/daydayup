package com.delegate;

public class DelegationTest {
    public static void main(String[] args) {
        C c = new C();
        c.f();
        c.g();

        c.toB();
        c.f();
        c.g();
    }
}
