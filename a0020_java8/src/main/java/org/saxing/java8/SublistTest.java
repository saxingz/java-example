package org.saxing.java8;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * sublist test
 *
 * @author saxing 2020/6/9 18:35
 */
public class SublistTest {


    public static void main(String[] args) {
        test3();
    }

    public static void test4(){
        Long time = 1558683433L;
        Date date = new Date(time * 1000);
        System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
    }

    public static void test3(){
        String stra = "abc\uD83E\uDDF9\uD83E\uDDF9\uD83E\uDDF912345678";
        System.out.println(stra);
        System.out.println(stra.substring(0, 4));


        String strb = "abc\uD83E\uDDF9\uD83E\uDDF9\uD83E\uDDF912345678";
        System.out.println(strb);
        strb = strb.substring(strb.offsetByCodePoints(0, 0),
                strb.offsetByCodePoints(0, 4));
        System.out.println(strb);

    }


    /**
     *
     */
    public static void test2() {
        String aaa = "\uD83D\uDE00";

    }

    public static void main2(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");

        List<String> l2 = list.subList(0, 1);

        System.out.println(list);
        System.out.println(l2);
        System.out.println();

    }

}
