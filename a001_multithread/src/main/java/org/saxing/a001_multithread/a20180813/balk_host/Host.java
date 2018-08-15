package org.saxing.a001_multithread.a20180813.balk_host;

import java.util.concurrent.TimeoutException;

public class Host {

    private final long timeout;
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void setExecutable(boolean on){
        ready = on;
        notifyAll();
    }

    //检查状态之后再执行
    public synchronized void execute() throws TimeoutException, InterruptedException {
        long start = System.currentTimeMillis();
        while (!ready){
            long now = System.currentTimeMillis();
            long rest = timeout - (now - start);
            if (rest <= 0){
                throw new TimeoutException("now - start = " + (now - start) + " timeout = " + timeout);
            }
            wait(rest);
        }
        doExecute();
    }

    private void doExecute() {
        System.out.println(Thread.currentThread().getName() + " calls execute");
    }


}
