package org.saxing.a001_multithread.a20180814.countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MyTask implements Runnable {

    private final CountDownLatch doneLatch;
    private final int context;
    private static final Random random = new Random(314159);

    public MyTask(CountDownLatch doneLatch, int context) {
        this.doneLatch = doneLatch;
        this.context = context;
    }

    @Override
    public void run() {
        doTask();
        doneLatch.countDown();
    }

    protected void doTask(){
        String name = Thread.currentThread().getName();
        System.out.println(name + ": MyTask:begin:context = " + context);
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
        }finally {
            System.out.println(name + ": MyTask:end:context = " + context);
        }
    }

}
