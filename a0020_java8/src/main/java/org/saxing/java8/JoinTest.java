package org.saxing.java8;

import sun.misc.Unsafe;

/**
 * join test
 *
 * @author saxing 2021/1/24 14:19
 */
public class JoinTest {

    public volatile static long i = 0;

    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (long j = 0L; j < 10000000000L; j++) {
                i += j;
            }
        }
    }

    public static void main2(String[] args) throws InterruptedException {
        AddThread add = new AddThread();
        add.start();
        add.join();
        System.out.println(i);
    }

    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        int i = unsafe.arrayBaseOffset(int[].class);
        System.out.println(i);
    }

}
