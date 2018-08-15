package org.saxing.a001_multithread.a20180814_handler.workpool;

public class WorkerThread extends Thread {

    private final Channel channel;
    private volatile boolean terminated = false;

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (!terminated){
            try {
                Request request = channel.takeRequest();
                request.execute();
            }catch (InterruptedException e){
                terminated = true;
            }
        }
    }

    public void stopThread() {
        terminated = true;
        interrupt();
    }
}
