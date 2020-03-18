package org.saxing.xiutest;

import java.util.ArrayList;
import java.util.List;

/**
 * test list
 *
 * @author saxing 2019/11/11 19:24
 */
public class TestList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        List<String> strings = list.subList(0, 5);
        System.out.println(strings);
    }

    public static void main3(String[] args) {
        int total = 10;
        int size = 5;
        int res = total / size + (total % size > 0 ? 1 : 0);
        System.out.println(res);
    }

    public static void main2(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");

        List<String> l3 = new ArrayList<>(list);
        l3.retainAll(list2);

        List<String> l4 = new ArrayList<>(list);
        l4.removeAll(list2);

        System.out.println(list);
        System.out.println(list2);
        System.out.println(l3);
        System.out.println(l4);
    }

}
