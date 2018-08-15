package org.saxing.a001_multithread.a20180814_handler.blackhole;

public class Main {

    public static void main(String[] args) {
        System.out.println("begin");
        Object obj = new Object();
        Blackhole .enter(obj);
        System.out.println("end");
    }

}
