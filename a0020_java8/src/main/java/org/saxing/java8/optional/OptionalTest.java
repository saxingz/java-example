package org.saxing.java8.optional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    public static void main(String[] args) throws Exception {
//        test2();
//        test3();
//        test4IfPresent();
//        test5OrElse();
//        test6OrElseGet();
//        test7OrElseThrow();
//        test8Filter();
//        test9Map();
        test10FlatMap();
    }

    public static void test10FlatMap(){
        Optional<Integer> optional1 = Optional.ofNullable(1);

        Optional<Optional<String>> str1Optional = optional1.map(a -> {
            return Optional.<String>of("key " + a);
        });

        Optional<String> str2Optional = optional1.flatMap(a -> {
            return Optional.<String>of("key " + a);
        });

        System.out.println(str1Optional.get().get());
        System.out.println(str2Optional.get());
    }

    public static void test9Map(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        Optional<String> strOption1 = optional1.map(a -> "key " + a);
        Optional<String> strOption2 = optional2.map(a -> "key " + a);

        // key 1
        System.out.println(strOption1.get());
        // false
        System.out.println(strOption2.isPresent());
    }

    public static void test8Filter(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        Optional<Integer> filter1 = optional1.filter(a -> a==null);
        Optional<Integer> filter2 = optional1.filter(a -> a==1);
        Optional<Integer> filter3 = optional2.filter(a -> a == null);

        System.out.println(filter1.isPresent());
        System.out.println(filter2.isPresent());
        System.out.println(filter2.get().intValue() == 1);
        System.out.println(filter3.isPresent());


    }

    public static void test7OrElseThrow() throws Exception{
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        try {
            optional1.orElseThrow(() -> {
                throw new IllegalStateException();
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            optional2.orElseThrow(() -> {
                throw new IllegalStateException();
            });
        }catch (Throwable e){
            e.printStackTrace();
        }


    }

    public static void test6OrElseGet(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // true true
        System.out.println(optional1.orElseGet(() -> {
            return 1000;
        }) == 1);
        System.out.println(optional2.orElseGet(() -> {
            return 1000;
        }) == 1000);
    }

    public static void test5OrElse(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // true true
        System.out.println(optional1.orElse(1000) == 1);
        System.out.println(optional2.orElse(1000) == 1000);
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
