package org.saxing.a001_multithread.a20180814.shutdown_hook;

public class Main {

    public static void main(String[] args) {
        System.out.println("main: begin");

        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("***");
                        System.out.println("UncaughtExceptionHandler begin");
                        System.out.println("currentThread = " + Thread.currentThread());
                        System.out.println("Thread = " + t);
                        System.out.println("exception = " + e);
                        System.out.println("UncaughtExceptionHandler end");
                    }
                }
        );
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("*****");
                System.out.println("shutdown begin");
                System.out.println("currentThread = " + Thread.currentThread());
                System.out.println("shutdown end");
            }
        });

        new Thread("mythread"){
            @Override
            public void run() {
                System.out.println("mythread start");
                System.out.println("mythread sleep...");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("mythread divide");

                int x = 1/0;

                System.out.println("mythread end");
            }
        }.start();

        System.out.println("main: end");
    }
}
