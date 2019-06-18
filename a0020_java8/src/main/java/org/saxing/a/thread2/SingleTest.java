package org.saxing.a.thread2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleTest {


    public static void main(String[] args) {

//        只有一个线程会死锁
        ExecutorService pool = Executors
                .newSingleThreadExecutor();
        pool.submit(() -> {
            try {

                String qq=pool.submit(()->"QQ").get();
                System.out.println(qq);
            } catch (Exception e) {
            }
        });

    }

}
