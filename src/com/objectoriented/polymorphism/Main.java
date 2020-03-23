package com.objectoriented.polymorphism;

/**
 * 例子中，我们实例化了两个Salary对象。一个使用Salary引用s，另一个使用Employee引用。
 *
 * 编译时，编译器检查到mailCheck()方法在Salary类中的声明。
 *
 * 在调用s.mailCheck()时，Java虚拟机(JVM)调用Salary类的mailCheck()方法。
 *
 * 因为e是Employee的引用，所以调用e的mailCheck()方法则有完全不同的结果。
 *
 * 当编译器检查e.mailCheck()方法时，编译器检查到Employee类中的mailCheck()方法。
 *
 * 在编译的时候，编译器使用Employee类中的mailCheck()方法验证该语句， 但是在运行的时候，Java虚拟机(JVM)调用的是Salary类中的mailCheck()方法。
 *
 * 该行为被称为虚拟方法调用，该方法被称为虚拟方法。
 *
 * Java中所有的方法都能以这种方式表现，借此，重写的方法能在运行时调用，不管编译的时候源代码中引用变量是什么数据类型。
 */
public class Main {
    public static void main(String[] args) {
        Salary s = new Salary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
        Employee e = new Salary("John Adams", "Boston, MA", 2, 2400.00);
        System.out.println("Call mailCheck using Salary reference --");
        s.mailCheck();
        System.out.println("\n Call mailCheck using Employee reference--");
        e.mailCheck();
    }
}

