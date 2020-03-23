package com.objectoriented;

public class Animal {

}

class Mammal extends Animal{

}

class Reptile extends Animal{

}

class Dog extends Mammal{
    public static void main(String[] args) {
        Animal a = new Animal();
        Mammal b = new Mammal();
        Dog c = new Dog();

        System.out.println(a instanceof Object);
        System.out.println(b instanceof Animal);
        System.out.println(c instanceof Mammal);
    }
}
