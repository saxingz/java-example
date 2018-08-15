package org.saxing.a001_multithread.a20180814.future3;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException {
        System.out.println("main begin");

        Host host = new Host();
        Data data1 = host.request(10, 'A');
        Data data2 = host.request(20, 'B');
        Data data3 = host.request(30, 'C');

        System.out.println("main other job begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main other job end");

        System.out.println("data1 = " + data1.getContent());
        System.out.println("data2 = " + data3.getContent());
        System.out.println("data3 = " + data2.getContent());

        System.out.println("main end");

    }

}
