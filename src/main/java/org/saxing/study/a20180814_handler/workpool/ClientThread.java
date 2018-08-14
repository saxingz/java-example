package org.saxing.study.a20180814_handler.workpool;

import java.util.Random;

public class ClientThread extends Thread {

    private final Channel channel;
    private static final Random random = new Random();
    private volatile boolean terminated = false;

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        for (int i = 0; !terminated; i++) {
            try {
                Request request = new Request(getName(), i);
                channel.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                terminated = true;
            }
        }

    }

    public void stopThread(){
    }
}
