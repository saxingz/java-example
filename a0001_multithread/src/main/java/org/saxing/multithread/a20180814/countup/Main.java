package org.saxing.multithread.a20180814.countup;

public class Main {

    public static void main(String[] args) {
        System.out.println("main begin");
        try {
            CountupThread t = new CountupThread();
            t.start();

            Thread.sleep(5000);

            System.out.println("main: shutdownRequest");
            t.shutdownRequest();

            System.out.println("main: join");
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main: end");

    }

}
