package org.saxing.mutex;

/**
 * Lock is an interface for a lock which can be acquired and released.
 *
 * @author saxing 2019/4/30 14:28
 */
public interface Lock {

    void acquire() throws InterruptedException;

    void release();

}
