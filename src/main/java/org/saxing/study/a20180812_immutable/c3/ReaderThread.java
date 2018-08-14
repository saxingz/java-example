package org.saxing.study.a20180812_immutable.c3;

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
