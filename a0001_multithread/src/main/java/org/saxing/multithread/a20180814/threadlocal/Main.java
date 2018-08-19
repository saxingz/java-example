package org.saxing.multithread.a20180814.threadlocal;

public class Main {

    public static void main(String[] args) {
        new ClientThread("Alice").start();
        new ClientThread("Bobby").start();
        new ClientThread("Chris").start();
    }

}
