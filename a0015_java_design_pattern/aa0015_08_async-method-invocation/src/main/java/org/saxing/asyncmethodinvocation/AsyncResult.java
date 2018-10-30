package org.saxing.asyncmethodinvocation;

import java.util.concurrent.ExecutionException;

/**
 * asyncresult interface
 *
 * @param <T>
 *
 * @author saxing  2018/10/30 9:22
 */
public interface AsyncResult<T> {

    /**
     * Status of the async task execution
     *
     * @return
     */
    boolean isComplete();

    /**
     * gets the value of completed async task
     *
     * @return
     * @throws ExecutionException
     */
    T getValue() throws ExecutionException;

    /**
     * Blocks the current thread until the async task is completed
     *
     * @throws InterruptedException
     */
    void await() throws InterruptedException;

}
