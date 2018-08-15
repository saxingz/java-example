package org.saxing.a001_multithread.a20180810;

public class Something {

    private int x = 0;
    private int y = 0;

    public synchronized void write(){
        x = 100;
        y = 50;
    }

    public synchronized void read(){
        if (x < y){
            System.out.println("x < y");
        }
        if (x == y){
            System.out.println("x = y");
        }
        if (x > y){
            System.out.println("x > y");
        }
    }
}

class Main{
    public static void main(String[] args) {
        final Something something = new Something();

        new Thread(){
            @Override
            public void run() {
                something.write();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                something.read();
            }
        }.start();

    }
}
