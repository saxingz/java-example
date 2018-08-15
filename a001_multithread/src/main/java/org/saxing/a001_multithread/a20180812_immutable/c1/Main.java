package org.saxing.a001_multithread.a20180812_immutable.c1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        new WriteThread(list).start();
        new ReaderThread(list).start();
    }

}
