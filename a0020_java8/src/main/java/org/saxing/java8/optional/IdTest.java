package org.saxing.java8.optional;

public class IdTest {

    public static Integer getType(Long entId){
        return (int)(entId / 1000000L);
    }

    public static void main(String[] args) {
        System.out.println(getType(100112L));
        System.out.println(getType(1100112L));
        System.out.println(getType(21100112L));
    }

}
