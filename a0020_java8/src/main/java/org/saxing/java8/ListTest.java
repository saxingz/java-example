package org.saxing.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");

        for (int i = 0; i < 100; i++) {
            System.out.println(list.get(new Random().nextInt(list.size())));
        }

    }

}
