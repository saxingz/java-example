package org.saxing.study.a20180811_thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main1 {

    public static void main(String[] args) {
        ThreadFactory factory = Executors.defaultThreadFactory();
        factory.newThread(new Printer("Nice!")).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("Good! ");
        }
    }

}
