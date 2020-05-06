package org.saxing.d_queue.aio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class T {


    //创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。创建线程池的时候请使用带ThreadFactory的构造函数，并且提供自定义ThreadFactory实现或者使用第三方实现。

    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("server-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();




    }

    class TimerTaskThread extends Thread {
        public TimerTaskThread() {
            super.setName("TimerTaskThread");
            // do something
        }
    }





}
