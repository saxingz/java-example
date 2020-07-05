package org.saxing.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * future test
 *
 * @author saxing 2020/7/5 23:41
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> call = () -> {
            Thread.sleep(3000);
            return "abcdefg";
        };
        FutureTask<String> futureTask = new FutureTask<>(call);
        new Thread(futureTask).start();

        Thread.sleep(1000);
        System.out.println("aaaa");

        String s = futureTask.get();
        System.out.println(s);
    }

}
