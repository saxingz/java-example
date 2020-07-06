package org.saxing.java8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic test
 *
 * @author saxing 2020/7/6 23:05
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger test = new AtomicInteger(0);
        test.incrementAndGet();
        test.incrementAndGet();
        test.incrementAndGet();

        System.out.println(test.get());
    }

}
