package org.saxing.multithread.a20180814.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int THREADS = 3;

    public static void main(String[] args) {
        System.out.println("begin");

        ExecutorService service = Executors.newFixedThreadPool(THREADS);

        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier Action");
            }
        };

        CyclicBarrier phaseBarrier = new CyclicBarrier(THREADS, barrierAction);

        //确认工作是否结束
        CountDownLatch doneLatch = new CountDownLatch(THREADS);

        try {
            for (int i = 0; i < THREADS; i++) {
                service.execute(new MyTask(phaseBarrier, doneLatch, i));
            }

            System.out.println("await");
            doneLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
            System.out.println("end");
        }

    }

}
