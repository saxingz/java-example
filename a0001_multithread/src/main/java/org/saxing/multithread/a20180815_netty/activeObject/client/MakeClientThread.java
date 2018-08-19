package org.saxing.multithread.a20180815_netty.activeObject.client;

import org.saxing.multithread.a20180815_netty.activeObject.activeobject.ActiveObject;
import org.saxing.multithread.a20180815_netty.activeObject.result.Result;

public class MakeClientThread extends Thread {

    private final ActiveObject activeObject;
    private final char fillchar;

    public MakeClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Result<String> result = activeObject.makeString(i, fillchar);
                Thread.sleep(10);
                String value = result.getResultValue();
                System.out.println(Thread.currentThread().getName() + ": value = " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
