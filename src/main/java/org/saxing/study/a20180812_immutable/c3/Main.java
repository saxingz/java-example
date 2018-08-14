package org.saxing.study.a20180812_immutable.c3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        new WriteThread(list).start();
        new ReaderThread(list).start();
    }

}
