package org.saxing.c_base;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * queue test
 *
 * @author saxing 2020/4/26 20:31
 *
 */
public class MyQueue {

    private LinkedList<Object> list = new LinkedList<>();

    private AtomicInteger count = new AtomicInteger();

    private final int minSize = 0;

    private final int maxSize;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    private final Object lock = new Object();

    private void put(Object obj) {
        synchronized (lock){
            while (count.get() == this.maxSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.incrementAndGet();
            System.out.println("新加入的元素为： " + obj);
            lock.notify();
        }
    }

    private Object take() {
        Object ret = null;
        synchronized (lock){
            while (count.get() == this.minSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ret = list.removeFirst();
            count.decrementAndGet();
            lock.notify();;
        }
        return ret;
    }

    public int getSize(){
        return this.count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        MyQueue myQueue = new MyQueue(5);
        myQueue.put("1");
        myQueue.put("2");
        myQueue.put("3");
        myQueue.put("4");
        myQueue.put("5");

        System.out.println("当前容器长度： " + myQueue.getSize());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("6");
                myQueue.put("7");
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = myQueue.take();
                System.out.println("被移除元素为：" + o1);
                Object o2 = myQueue.take();
                System.out.println("被移除元素为：" + o2);
            }
        }, "t2");

        TimeUnit.SECONDS.sleep(2);

        t2.start();

    }

}
































