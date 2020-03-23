package com.abstractfactory;

/**
 * 抽象工厂模式是另一个创建模式。
 *
 * 抽象工厂模式，也称为工厂的工厂，有一个工厂创建其他工厂。
 *
 * 当使用抽象工厂模式时，我们首先使用超级工厂创建工厂，然后使用创建的工厂创建对象。
 */
public abstract class AbstractFactory {
    abstract Printer getPrinter(String type);
    abstract Shape getShape(String shape);
}
