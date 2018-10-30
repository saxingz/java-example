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
        CompletableResult<T> result = new CompletableResult<>(callback);
        new Thread(() -> {
            try {
                result.setValue(task.call());
            } catch (Exception e) {
                result.setException(e);
            }
        }, "executor-" + idx).start();
        return null;
    }

    @Override
    public <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException {
        if (!asyncResult.isComplete()){
            asyncResult.await();
        }
        return asyncResult.getValue();
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

        public CompletableResult(AsyncCallback<T> callback) {
            lock = new Object();
            this.callback = Optional.ofNullable(callback);
        }

        /**
         *
         * @param value
         */
        void setValue(T value){
            this.value = value;
            this.state = COMPLETED;
            this.callback.ifPresent(ac -> ac.onComplete(value, Optional.<Exception>empty()));
            synchronized (lock){
                lock.notifyAll();
            }
        }

        /**
         *
         * @param exception
         */
        void setException(Exception exception){
            this.exception = exception;
            this.state = FAILED;
            this.callback.ifPresent(ac -> ac.onComplete(null, Optional.of(exception)));
            synchronized (lock){
                lock.notifyAll();
            }
        }

        @Override
        public boolean isComplete() {
            return state > RUNNING;
        }

        @Override
        public T getValue() throws ExecutionException {
            if (state == COMPLETED){
                return value;
            } else if (state == FAILED){
                throw new ExecutionException(exception);
            } else {
                throw new IllegalStateException("Execution is not completed yet");
            }
        }

        @Override
        public void await() throws InterruptedException {
            synchronized (lock){
                while (!isComplete()){
                    lock.wait();
                }
            }
        }
    }
}
