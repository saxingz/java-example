package org.saxing.multithread.a20180812_single;

public class Main {

    public static void main(String[] args) {
        System.out.println("Testing gate...");
        Gate gate = new Gate();
        new UserThread(gate, "aname", "aaddress").start();
        new UserThread(gate, "bname", "baddress").start();
        new UserThread(gate, "cname", "caddress").start();
    }

}
