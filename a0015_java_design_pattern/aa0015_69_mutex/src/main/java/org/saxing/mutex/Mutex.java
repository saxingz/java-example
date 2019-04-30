package org.saxing.mutex;

/**
 * Mutex is an implementation of a mutual exclusion lock.
 *
 * @author saxing 2019/4/30 14:32
 */
public class Mutex implements Lock {

    /**
     * The current owner of the lock.
     */
    private Object owner;

    /**
     * Returns the current owner of the Mutex, or null if available
     */
    public Object getOwner() {
        return owner;
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        while (owner != null) {
            wait();
        }

        owner = Thread.currentThread();
    }

    @Override
    public synchronized void release() {
        if (Thread.currentThread() == owner) {
            owner = null;
            notify();
        }
    }
}
