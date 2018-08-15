package org.saxing.a001_multithread.a20180812_immutable.c2;

import org.saxing.a001_multithread.a20180812_immutable.c1.WriteThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        new WriteThread(list).start();
        new ReaderThread2(list).start();
    }

}
