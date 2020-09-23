package org.saxing.java8;

/**
 * volati
 */
public class VolatileTest {

    public static int inc = 0;

    public static synchronized void increase() {
        inc++;
    }

    public static void main1(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++){
            int finalI = i;
            new Thread(() -> {
                for(int j=0;j<1000;j++) {
                    increase();
                }
            }).start();
        }


        //保证前面的线程都执行完
        while (Thread.activeCount() > 2) {
//            Thread.yield();
            System.out.println("Thread.activeCount(): " + Thread.activeCount());

        }
        System.out.println(inc);
    }

    public static void main(String[] args) {
        ReaderThread thread = new ReaderThread(true, 0);
        new Thread(thread).start();

        for (int i = 0; i < 1000000; i++) {
            if (i == 950000) {
                thread.data = i;
                thread.isRunning = false;
            }
        }


    }

    private static class ReaderThread implements Runnable {
        volatile boolean isRunning;
        int data;

        public ReaderThread(boolean isRunning, int data) {
            this.isRunning = isRunning;
            this.data = data;
        }

        @Override
        public void run() {
            while (isRunning) {

            }
            System.out.println(isRunning);
            System.out.println(data);
        }
    }
}
