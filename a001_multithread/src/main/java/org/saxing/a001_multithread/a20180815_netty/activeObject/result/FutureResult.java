package org.saxing.a001_multithread.a20180815_netty.activeObject.result;

public class FutureResult<T> extends Result {

    private Result<T> result;
    private boolean ready = false;

    public synchronized void setResult(Result<T> result) {
        this.result = result;
        ready = true;
        notifyAll();
    }

    @Override
    public synchronized T getResultValue() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return result.getResultValue();
    }
}
