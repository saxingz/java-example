package org.saxing.a001_multithread.a20180812_single;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Log{
    public static void println(String s){
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}

class BoundedResource{
    private final Semaphore semaphore;
    private final int permits;
    private final static Random random = new Random(314159);

    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    public void use() throws InterruptedException{
        semaphore.acquire();
        try {
            doUse();
        }finally {
            semaphore.release();
        }
    }


    protected void doUse() throws InterruptedException{
        Log.println("BEGIN: use = " + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        Log.println("END: use = " + (permits - semaphore.availablePermits()));
    }

}

class UserThread2 extends Thread{
    private final static Random random = new Random(26535);
    private final BoundedResource resource;

    public UserThread2(BoundedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true){
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {

        }
    }
}


public class Main2 {

    public static void main(String[] args) {
        BoundedResource resource = new BoundedResource(3);

        for (int i = 0; i < 10; i++) {
            new UserThread2(resource).start();
        }
    }

}
