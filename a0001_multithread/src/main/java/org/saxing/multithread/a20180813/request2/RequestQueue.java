package org.saxing.multithread.a20180813.request2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {

    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Request getRequest(){
        Request req = null;
        try {
            req = queue.take();
        } catch (InterruptedException e) {

        }
        return req;
    }

    public void putRequest(Request req){
        try {
            queue.put(req);
        } catch (InterruptedException e) {
        }
    }

}
