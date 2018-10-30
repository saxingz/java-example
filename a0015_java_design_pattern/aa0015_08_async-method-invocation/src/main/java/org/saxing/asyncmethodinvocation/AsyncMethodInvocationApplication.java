package org.saxing.asyncmethodinvocation;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * async method invocation
 *
 * @author saxing  2018/10/30 11:24
 */
//@SpringBootApplication
public class AsyncMethodInvocationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncMethodInvocationApplication.class);

//    public static void main(String[] args) {
//        SpringApplication.run(AsyncMethodInvocationApplication.class, args);
//    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AsyncExecutor executor = new ThreadAsyncExecutor();
        AsyncResult<Integer> asyncResult1 = executor.startProcess(lazyval(10, 500));
        AsyncResult<String> asyncResult2 = executor.startProcess(lazyval("test", 300));
        AsyncResult<Long> asyncResult3 = executor.startProcess(lazyval(50L, 700));
        AsyncResult<Integer> asyncResult4 = executor.startProcess(lazyval(20, 400), callback("Callback result 4"));
        AsyncResult<String> asyncResult5 = executor.startProcess(lazyval("callback", 600), callback("Callback result 5"));

        Thread.sleep(300);
        log("some hard work done");

        Integer result1 = executor.endProcess(asyncResult1);
        String result2 = executor.endProcess(asyncResult2);
        Long result3 = executor.endProcess(asyncResult3);
        asyncResult4.await();
        asyncResult5.await();

        log("Result1: " + result1);
        log("Result2: " + result2);
        log("Result3: " + result3);
    }

    private static <T> Callable<T> lazyval(T value, long delayMillis){
        return () -> {
            Thread.sleep(delayMillis);
            log("Task completed with " + value);
            return value;
        };
    }

    private static <T> AsyncCallback<T> callback(String name){
        return ((value, ex) -> {
            if (ex.isPresent()){
                log("async callback: " + name + " failed: " + ex.map(Exception::getMessage).orElse(""));
            }else {
                log("async callback: " + name + ": " + value);
            }
        });
    }

    private static void log(String msg){
        LOGGER.info(msg);
    }

}
