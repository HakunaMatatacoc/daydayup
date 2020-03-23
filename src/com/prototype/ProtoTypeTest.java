package com.prototype;

/**
 * 原型模式是创建模式之一。
 *
 * 原型模式有助于创建具有更好性能的重复对象。
 *
 * 在原型模式中，将返回一个现有对象的克隆，而不是创建新的对象。
 *
 * 我们使用原型设计模式，如果创建一个新对象的成本是昂贵和资源密集型。
 */
public class ProtoTypeTest {
    public static void main(String[] args) {
        ShapeProtoType.losdCache();

        Shape cloneShape = (Shape)ShapeProtoType.getShape("1");
        System.out.println("Shape: " + cloneShape.getType());

        Shape cloneShape2 = (Shape)ShapeProtoType.getShape("2");
        System.out.println("Shape: " + cloneShape2.getType());

        Shape cloneShape3 = (Shape)ShapeProtoType.getShape("3");
        System.out.println("Shape: " + cloneShape3.getType());
    }
}
