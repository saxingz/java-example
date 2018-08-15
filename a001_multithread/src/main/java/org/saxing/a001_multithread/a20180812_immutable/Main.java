package org.saxing.a001_multithread.a20180812_immutable;

public class Main {

    public static void main(String[] args) {
        Person alice = new Person("Alice", "Alaska");
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
    }

}
