package org.saxing.a001_multithread.a20180813.queue;

import org.saxing.study.a20180813.request2.Request;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class RequestQueue {
    private static final long TIME_OUT = 10000L;
    private final Queue<Request> queue = new LinkedList<>();
    public synchronized Request getRequest() throws TimeoutException {
        long start = System.currentTimeMillis();
        while (queue.peek() == null){
            long now = System.currentTimeMillis();
            long rest = TIME_OUT - (now - start);
            if (rest <= 0){
                throw new TimeoutException("time out");
            }
            try {
                wait(rest);
            } catch (InterruptedException e) {

            }
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }

}
