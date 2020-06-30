package com.jianli.util.lambda;

/**
 * 该接口是一个函数式接口,只有一个抽象方法
 * @param <T>
 */

@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
