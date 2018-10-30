package org.saxing.asyncmethodinvocation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * async executor interface
 *
 * @author saxing  2018/10/30 9:21
 *
 */
public interface AsyncExecutor {

    /**
     * Start processing of an async task, Returns immediately with async result.
     *
     * @param task
     * @param <T>
     * @return
     */
    <T> AsyncResult<T> startProcess(Callable<T> task);

    /**
     * Start a processing of an async task, Returns immediately with async result. Executes callbask
     *
     * @param task
     * @param callback
     * @param <T>
     * @return
     */
    <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback);

    /**
     * Ends processing of an async task. Blocks the current thread if necessary and returns the
     * evaluated value of the completed task.
     *
     * @param asyncResult
     * @param <T>
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException;



}
