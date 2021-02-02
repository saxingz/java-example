package org.saxing.java8;

public class ThreadTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                long id = Thread.currentThread().getId();
                System.out.println(id);
            }).start();
        }
    }


}
