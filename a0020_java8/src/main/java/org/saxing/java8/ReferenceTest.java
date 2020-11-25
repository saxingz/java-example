package org.saxing.java8;

import com.google.common.primitives.UnsignedInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReferenceTest {

    static class TTT {
        Integer tt;

        public Integer getTt() {
            return tt;
        }

        public void setTt(Integer tt) {
            this.tt = tt;
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        addList(list);
        System.out.println(list);

        Integer[] is = new Integer[]{1};
        change(is);
        System.out.println(is[0]);

        UnsignedInteger i2 = UnsignedInteger.valueOf(2);
        change2(i2);
    }

    private static void change(Integer[] is) {
        is[0] = 2;
    }

    private static void change2(UnsignedInteger is) {
        is = is.plus(UnsignedInteger.valueOf(5));
    }



    private static void addList(List<String> list) {
        list.add("aa");
        list.add("aa");
        list.add("aa");
        list.add("aa");
        list.add("aa");
        list.add("aa");
        list.add("aa");
    }

}
