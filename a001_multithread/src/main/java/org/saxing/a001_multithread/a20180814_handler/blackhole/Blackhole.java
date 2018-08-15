package org.saxing.a001_multithread.a20180814_handler.blackhole;

public class Blackhole {

    public static void enter(Object obj){
        System.out.println("Step 1");
//        magic(obj);
        magic2(obj);
        System.out.println("Step 2");
        synchronized (obj){
            System.out.println("step 3 (never reached here)");
        }
    }

    private static void magic2(Object obj) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (obj){
                    synchronized (this){
                        this.notifyAll();
                    }
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        synchronized (thread){
            thread.start();
            try {
                thread.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    private static void magic(Object obj) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (obj){
                    synchronized (this){
                        this.setName("Locked");
                        this.notifyAll();
                    }
                    while (true){

                    }
                }
            }
        };
        synchronized (thread){
            thread.setName("");
            thread.start();
            while (thread.getName().equals("")){
                try {
                    thread.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }

}
