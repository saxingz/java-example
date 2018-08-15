package org.saxing.a001_multithread.a20180813.request3;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue {

    private final Queue<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() throws InterruptedException {
        while (queue.peek() == null){
            wait();
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }

}
