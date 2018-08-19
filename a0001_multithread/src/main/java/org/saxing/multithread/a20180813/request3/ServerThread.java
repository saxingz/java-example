package org.saxing.multithread.a20180813.request3;

import java.util.Random;

public class ServerThread extends Thread {

    private final Random random;
    private final RequestQueue queue;

    public ServerThread(RequestQueue queue, String name, long seed) {
        super(name);
        random = new Random(seed);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                Request request = queue.getRequest();
                System.out.println(currentThread().getName() + " hanldes " + request);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e){
        }

    }
}
