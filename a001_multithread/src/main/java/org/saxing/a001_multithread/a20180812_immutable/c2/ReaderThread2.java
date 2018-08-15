package org.saxing.a001_multithread.a20180812_immutable.c2;

import java.util.List;

public class ReaderThread2 extends Thread {

    private final List<Integer> list;

    public ReaderThread2(List<Integer> list) {
        super("readerthread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            synchronized (list){
                for (int n : list){
                    System.out.println(n);
                }
            }
        }

    }
}
