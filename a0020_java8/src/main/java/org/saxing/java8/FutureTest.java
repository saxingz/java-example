package org.saxing.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * future test
 *
 * @author saxing 2020/7/5 23:41
 */
public class FutureTest {


    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Long, Integer> teacherVisibleNum = new ConcurrentHashMap<>();
        Callable<Void> callable = () -> {
            for (int i = 0; i < 1000; i++) {
                teacherVisibleNum.put(i + 200L, i);
                System.out.println("add ...");
            }
            return null;
        };
        FutureTask<Void> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        Thread.sleep(500);

        try {
            futureTask.get();
            System.out.println(teacherVisibleNum);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("success");

    }

    public static void main2(String[] args) throws InterruptedException, ExecutionException {
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
