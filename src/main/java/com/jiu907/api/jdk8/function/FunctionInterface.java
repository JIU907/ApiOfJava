package com.jiu907.api.jdk8.function;

/**
 * @Author LeiLiMin
 * @Description: Jdk8: 函数式接口
 * @date: 2022/1/16
 */
@FunctionalInterface
public interface FunctionInterface {
    /**
     * what: 如果这个接口只有一个抽象方法，那么该接口就是一个函数式接口
     * @FunctionalInterface: 如果在接口上定义了该注解，那么编译器会按照函数式接口的要求来定义该接口
     *                       如果接口没有定义该注解，但是该接口仍然符合函数式接口的定义，那么编译器仍然认为这是一个函数式接口
     */
    void test();
}
