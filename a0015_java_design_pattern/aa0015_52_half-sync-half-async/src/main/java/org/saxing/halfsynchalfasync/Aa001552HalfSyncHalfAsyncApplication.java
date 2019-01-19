package org.saxing.halfsynchalfasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * main
 *
 * @author saxing 2019/1/19 9:59
 */
public class Aa001552HalfSyncHalfAsyncApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001552HalfSyncHalfAsyncApplication.class);

    public static void main(String[] args) {
        AsynchronousService service = new AsynchronousService(new LinkedBlockingQueue<>());

        service.execute(new ArithmeticSumTask(1000));
        service.execute(new ArithmeticSumTask(500));
        service.execute(new ArithmeticSumTask(2000));
        service.execute(new ArithmeticSumTask(1));
        service.execute(new ArithmeticSumTask(-1));

    }

    static class ArithmeticSumTask implements AsyncTask<Long>{

        private long n;

        public ArithmeticSumTask(long n) {
            this.n = n;
        }

        @Override
        public Long call() throws Exception {
            return ap(n);
        }

        @Override
        public void onPreCall()  {
            if (n < 0) {
                throw new IllegalArgumentException("n is less than 0");
            }
        }

        @Override
        public void onPostCall(Long result) {
            // Handle the result of computation
            LOGGER.info(result.toString());
        }

        @Override
        public void onError(Throwable throwable) {
            throw new IllegalStateException("Should not occur");
        }
    }

    private static long ap(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            LOGGER.error("Exception caught.", e);
        }
        return i * (i + 1) / 2;
    }

}

