package org.saxing.multithread.a20180813.producer2;

import java.util.concurrent.ArrayBlockingQueue;

public class Table extends ArrayBlockingQueue<String> {

    public Table(int count) {
        super(count);
    }

    @Override
    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        super.put(cake);
    }

    @Override
    public synchronized String take() throws InterruptedException {
        String cake = super.take();
        System.out.println(Thread.currentThread().getName() + " take " + cake);
        return cake;
    }

}
