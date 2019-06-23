package org.saxing.a.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;

public class FstThreadLocalTest {

    public static void main(String[] args) {
        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        fastThreadLocal.set("test fastThreadLocal set");
        System.out.println(fastThreadLocal.get());
        fastThreadLocal.remove();
        System.out.println(fastThreadLocal.get());
    }

}
