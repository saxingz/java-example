package org.saxing.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ffffffffff");
        });

        System.out.println("aaaaaaaaaaa");
        executorService.shutdown();
        System.out.println("bbbbbbbbbbbbbbbbbbbb");

    }

}
