package org.saxing.a.thread2;

public class SynchronizedTest {

    public static void main(String[] args) {
        new Abc().AA();
    }

}


class Abc{

    public synchronized void AA(){
        System.out.println("aa");
        BB();
    }

    public synchronized void BB(){
        System.out.println("bb");
    }

}
