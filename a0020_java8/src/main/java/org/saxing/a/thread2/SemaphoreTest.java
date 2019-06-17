package org.saxing.a.thread2;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static int count;
    // 初始化信号量
    static final Semaphore s
            = new Semaphore(1);
    // 用信号量保证互斥
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count+=1;
        } finally {
            s.release();
        }
    }


}
