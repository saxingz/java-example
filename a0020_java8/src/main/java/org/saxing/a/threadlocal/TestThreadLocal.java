package org.saxing.a.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal {

    final static ThreadLocal<String> LOCAL = new ThreadLocal();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            // 存值
            LOCAL.set(Thread.currentThread().getName());
            // 获取值
            System.out.println(Thread.currentThread().getName() + "-->" +LOCAL.get());
        });

        executorService.execute(() -> {
            // 存值
            LOCAL.set(Thread.currentThread().getName());
            // 获取值
            System.out.println(Thread.currentThread().getName() + "-->" +LOCAL.get());
        });

        executorService.shutdown();
    }


}
