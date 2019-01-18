package org.saxing.guardedsuspension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * guarded queue
 *
 * @author saxing 2019/1/18 23:01
 */
public class GuardedQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuardedQueue.class);
    private final Queue<Integer> sourceList;

    public GuardedQueue() {
        sourceList = new LinkedList<>();
    }

    public synchronized Integer get(){
        while (sourceList.isEmpty()){
            try {
                LOGGER.info("waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("getting");
        return sourceList.peek();
    }

    public synchronized void put(Integer e){
        LOGGER.info("putting");
        sourceList.add(e);
        LOGGER.info("notifying");
        notify();
    }
}
