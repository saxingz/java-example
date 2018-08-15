package org.saxing.a001_multithread.a20180814.future;

public class FutureData implements Data {

    private RealData realData = null;
    private boolean ready = false;

    @Override
    public synchronized String getContent() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.getContent();
    }

    public synchronized void setRealData(RealData realData) {
        if (ready){
            return;
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }
}
