package org.saxing.multithread.a20180812_immutable.c1;

import java.util.List;

public class ReaderThread extends Thread {

    private final List<Integer> list;

    public ReaderThread(List<Integer> list) {
        super("readerthread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            for (int n : list){
                System.out.println(n);
            }
        }

    }
}
