package org.saxing.java8.optional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    public static void main(String[] args) {
//        test2();
//        test3();
//        test4IfPresent();
    }


    public static void test4IfPresent(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        optional1.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("value is " + integer);
            }
        });

        optional2.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("value is " + integer);
            }
        });
    }

    public static void test3(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // true
        System.out.println(optional1.isPresent() == true);
        // true
        System.out.println(optional2.isPresent() == false);
    }

    public static void test2(){
        Optional<Integer> optional1 = Optional.ofNullable(null);
        Optional<Integer> optional2 = Optional.ofNullable(null);
        // true
        System.out.println(optional1 == optional2);

        // true
        System.out.println(optional1 == Optional.<Integer>empty());

        Object o1 = Optional.<Integer>empty();
        Object o2 = Optional.<String>empty();
        // true
        System.out.println(o1 == o2);
    }

    public static void test1(){
        Optional<Integer> optional1 = Optional.of(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);
        Optional<Integer> optional3 = Optional.ofNullable(2);
    }

}
