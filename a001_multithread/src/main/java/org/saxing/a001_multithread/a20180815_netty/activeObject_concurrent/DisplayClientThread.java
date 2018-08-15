package org.saxing.a001_multithread.a20180815_netty.activeObject_concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.RejectedExecutionException;

public class DisplayClientThread extends Thread {

    private final ActiveObject activeObject;


    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String str = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(str);
                Thread.sleep(200);
            }
        } catch (RejectedExecutionException e){
            System.out.println(Thread.currentThread().getName() + ": " + e);
        } catch (CancellationException e){
            System.out.println(Thread.currentThread().getName() + ": " + e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
