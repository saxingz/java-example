package org.saxing.java8;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger test = new AtomicInteger(0);
        test.incrementAndGet();
        test.incrementAndGet();
        test.incrementAndGet();

        System.out.println(test.get());
    }

}
