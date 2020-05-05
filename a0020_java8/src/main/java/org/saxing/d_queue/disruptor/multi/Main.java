package org.saxing.d_queue.disruptor.multi;


import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Main
 *
 * @author saxing 2020/5/5 19:57
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建ringBuffer
        RingBuffer<Order> ringBuffer =
                RingBuffer.create(ProducerType.MULTI, Order::new, 1024 * 1024, new YieldingWaitStrategy());

        SequenceBarrier barriers = ringBuffer.newBarrier();

        Consumer[] consumers = new Consumer[3];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("c" + i);
        }

        WorkerPool<Order> workerPool =
                new WorkerPool<>(ringBuffer, barriers, new IntEventExceptionHandler(), consumers);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            final Producer p = new Producer(ringBuffer);
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    String uid = UUID.randomUUID().toString();
                    p.onData(uid);
                    System.out.println("生产完毕： uid: " + uid);
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println("---------------开始生产-----------------");
        latch.countDown();
        Thread.sleep(5000);
        System.out.println("总数:" + consumers[0].getCount() );
    }

    static class IntEventExceptionHandler implements ExceptionHandler<Order> {

        @Override
        public void handleEventException(Throwable throwable, long l, Order order) {}
        @Override
        public void handleOnStartException(Throwable ex) {}
        @Override
        public void handleOnShutdownException(Throwable ex) {}
    }

}
