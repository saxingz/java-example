package org.saxing.a001_multithread.a20180813.producer3;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        Exchanger<char[]> exchanger = new Exchanger<>();
        char[] buffer1 = new char[10];
        char[] buffer2 = new char[10];
        new ProducerThread(exchanger, buffer1, 31452).start();
        new ConsumerThread(exchanger, buffer2, 33214).start();
    }

}
