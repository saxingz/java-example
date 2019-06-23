package org.saxing.a.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal2 {

    final static ThreadLocal<String> LOCAL = new ThreadLocal();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            // 存值
            LOCAL.set(Thread.currentThread().getName());
            try {
                // 停顿一秒，以便先在gc，再get
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取值
            System.out.println(Thread.currentThread().getName() + "-->" +LOCAL.get());
        });
        // 线程二
        executorService.execute(() -> {
            LOCAL.set(Thread.currentThread().getName());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" +LOCAL.get());
        });
        // 主线程中触发gc
        System.gc();
        executorService.shutdown();
    }

}
