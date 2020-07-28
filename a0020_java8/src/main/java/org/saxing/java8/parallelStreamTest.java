package org.saxing.java8;

import java.util.ArrayList;
import java.util.List;

public class parallelStreamTest {

    public static void main(String[] args) {
//        test1();
        test2();

        System.out.println("ffffffffffffffffffffffff");
    }

    public static void test2(){
        List<String> str = new ArrayList<>();

        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");
        str.add("7");
        str.add("8");
        str.add("9");
        str.add("0");

        str.parallelStream().forEach(s -> {
            System.out.println(s);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void test1(){
        List<String> str = new ArrayList<>();

        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");
        str.add("7");
        str.add("8");
        str.add("9");
        str.add("0");

        str.forEach(s -> {
            System.out.println(s);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
