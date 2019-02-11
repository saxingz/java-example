package org.saxing.java8.optional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListTest {


    public static void main4(String[] args) {
        String str = "/boss/v1/enterprises";
        System.out.println(str.split("/")[1]);
    }

    public static void main5(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        String res = org.apache.commons.lang3.StringUtils.join(set1, ",");
        System.out.println(res);
        String res2 = org.apache.commons.lang3.StringUtils.join(new HashSet<>(), ",");
        System.out.println(res2);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);
        set1.retainAll(set2);

        System.out.println(set1);
    }

    public static void main(String[] args) {
        testThread();
    }

    public static void testThread(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bbbbb");
        });
        System.out.println("aaaaa");
    }

    public static void testList() {
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
        list2.add("A7");

        List<String> res1 = new ArrayList<>(list);
        res1.removeAll(list2);

        System.out.println(list);
        System.out.println(res1);


//        list.retainAll(list2);
//
//        System.out.println(list);
//
//        System.out.println(list.size() / 4);
//
//        List<String> strings = list.subList(0, 3);
//        System.out.println(strings);

    }

}
