package org.saxing.objectpool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oliphaunt
 *
 * @author saxing 2019/5/9 15:29
 */
public class Oliphaunt {

    private static AtomicInteger counter = new AtomicInteger(0);

    private final int id;

    public Oliphaunt() {
        id = counter.incrementAndGet();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Oliphaunt id=%d", id);
    }
}
