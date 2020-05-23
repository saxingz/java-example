package org.saxing.java8;

import java.util.*;

/**
 * set test
 *
 * @author saxing 2020/5/23 15:11
 */
public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("2020-05-23");
//        set.add("2020-05-21");
//        set.add("2020-05-25");
//        set.add("2020-05-28");
//        set.add("2020-05-24");

        TreeSet<String> treeSet = new TreeSet<>(set);
        System.out.println(treeSet);


        Integer setIndex = getSetPosition(treeSet, "2020-05-23");
        System.out.println(setIndex);

    }




    private static Integer getSetPosition(TreeSet<String> treeSet, String element){
        Iterator<String> iterator = treeSet.iterator();
        int i = 0;
        while (iterator.hasNext()){
            if (Objects.equals(iterator.next(), element)){
                return ++i;
            }
            ++i;
        }
        return 0;
    }

}
