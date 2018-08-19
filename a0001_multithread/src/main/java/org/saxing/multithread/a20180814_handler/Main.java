package org.saxing.multithread.a20180814_handler;

public class Main {

    public static void main(String[] args) {
        System.out.println("main begin");
        Host host = new Host();
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("main end");
    }

}
