package org.saxing.a001_multithread.a20180813.heavyHost;

public class Main {

    public static void main(String[] args) {
        Thread executor = new Thread(){
            @Override
            public void run() {
                System.out.println("Host.execute begin...");
                try {
                    Host.execute(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Host.execute end.");
            }
        };

        executor.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("***  interrupt ***");
        executor.interrupt();
    }

}
