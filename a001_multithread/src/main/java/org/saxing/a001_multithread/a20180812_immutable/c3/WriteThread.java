package org.saxing.a001_multithread.a20180812_immutable.c3;

import java.util.List;

public class WriteThread extends Thread {

    private final List<Integer> list;

    public WriteThread(List<Integer> list) {
        super("writethread");
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);

        }

    }
}
