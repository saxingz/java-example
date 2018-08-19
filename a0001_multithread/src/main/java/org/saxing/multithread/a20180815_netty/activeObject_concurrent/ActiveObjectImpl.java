package org.saxing.multithread.a20180815_netty.activeObject_concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ActiveObjectImpl implements ActiveObject {

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    public Future<String> makeString(int count, char fillchar) {
        class MakeStringRequest implements Callable<String>{
            @Override
            public String call() throws Exception {
                char[] buffer = new char[count];
                for (int i = 0; i < count; i++) {
                    buffer[i] = fillchar;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                return new String(buffer);
            }
        }
        return service.submit(new MakeStringRequest());
    }

    @Override
    public void displayString(String string) {
        class DisplayRequest implements Runnable{

            @Override
            public void run() {
                try {
                    System.out.println("display: " + string);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        }
        service.execute(new DisplayRequest());
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }
}
