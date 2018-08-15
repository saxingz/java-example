package org.saxing.a001_multithread.a20180813.producer2;

import java.util.Random;

public class EaterThread extends Thread {

    private final Random random;
    private final Table table;

    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            while (true){
                String cake = table.take();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
