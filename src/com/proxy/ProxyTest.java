package com.proxy;

/**
 * 在代理模式中，一个类代表另一个类的功能。
 *
 * 代理模式是一种结构模式。
 *
 * 在代理模式中，我们创建具有原始接口的对象，以将其功能暴露给外部世界。
 */
public class ProxyTest {
    public static void main(String[] args) {
        ProxyPrinter image = new ProxyPrinter("test");
        image.print();
    }
}
