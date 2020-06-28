package org.saxing.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * executor test
 *
 * @author saxing 2020/6/28 23:36
 */
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
