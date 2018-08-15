package org.saxing.a001_multithread.a20180813.producer;

import java.util.Random;

public class MakerThread extends Thread {

    private final Random random;
    private final Table table;
    private static int id = 0;

    public MakerThread(String name, Table table, long seed) {
        super(name);
        random = new Random(seed);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(random.nextInt(1000));
                String cake = "[ cake No." + nextId() + "by name : " + getName() + "]";
                table.put(cake);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int nextId(){
        return id++;
    }
}
