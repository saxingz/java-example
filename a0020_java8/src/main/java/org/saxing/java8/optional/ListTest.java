package org.saxing.java8.optional;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("A2");
        list.add("A3");
        list.add("A4");
        list.add("A5");
        list.add("A6");

        List<String> list2 = new ArrayList<>();
        list2.add("A3");
        list2.add("A4");
        list2.add("A5");
        list2.add("A6");

        list.retainAll(list2);

        System.out.println(list);

        System.out.println(list.size() / 4);

        List<String> strings = list.subList(0, 3);
        System.out.println(strings);

    }

}
