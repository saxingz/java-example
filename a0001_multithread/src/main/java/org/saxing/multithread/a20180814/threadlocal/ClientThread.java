package org.saxing.multithread.a20180814.threadlocal;

public class ClientThread extends Thread {

    public ClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + ": begin");
        for (int i = 0; i < 10; i++) {
            Log.println("i=" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.close();
        System.out.println(getName() + ": end");
    }
}
