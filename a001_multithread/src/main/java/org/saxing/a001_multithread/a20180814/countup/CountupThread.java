package org.saxing.a001_multithread.a20180814.countup;

public class CountupThread extends Thread {

    private long counter = 0;

    private volatile boolean shutdownRequested = false;

    public void shutdownRequest(){
        shutdownRequested = true;
        interrupt();
    }

    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    @Override
    public final void run() {
        try {
            while (!isShutdownRequested()){
                doWork();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            doShutdown();
        }
    }

    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    public void doShutdown(){
        System.out.println("doshutdown counter = " + counter);
    }
}
