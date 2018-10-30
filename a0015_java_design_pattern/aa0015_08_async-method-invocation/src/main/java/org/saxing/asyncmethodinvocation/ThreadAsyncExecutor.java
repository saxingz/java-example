package org.saxing.asyncmethodinvocation;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Implementation of async executor that creates a new thread for every task.
 *
 * @author saxing  2018/10/30 9:35 
 */
public class ThreadAsyncExecutor implements AsyncExecutor {

    /* Index for thread naming */
    private final AtomicInteger idx = new AtomicInteger(0);

    @Override
    public <T> AsyncResult<T> startProcess(Callable<T> task) {
        return startProcess(task, null);
    }

    @Override
    public <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback) {
        
        return null;
    }

    @Override
    public <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException {
        return null;
    }

    /**
     * Simple implementation of async result that allows completing it successfully with a value or exceptionally with an
     * exception. A really simplified version from its real life cousins FutureTask and CompletableFuture.
     * 
     * @param <T>
     * 
     * @author saxing  2018/10/30 9:39
     */
    private class CompletableResult<T> implements AsyncResult<T>{

        static final int RUNNING = 1;
        static final int FAILED = 2;
        static final int COMPLETED = 3;

        final Object lock;
        final Optional<AsyncCallback<T>> callback;

        volatile int state = RUNNING;
        T value;
        Exception exception;

        public CompletableResult(Optional<AsyncCallback<T>> callback) {
            lock = new Object();
            this.callback = callback;
        }

        @Override
        public boolean isComplete() {
            return false;
        }

        @Override
        public T getValue() throws ExecutionException {
            return null;
        }

        @Override
        public void await() throws InterruptedException {

        }
    }
}
