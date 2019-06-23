package org.saxing.a.threadlocal;

public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<String> local = new MyThreadLocal<>();
        System.out.println(local.get());
        local.set("hello");
        System.out.println(local.get());
        local.remove();
        System.out.println(local.get());
    }

}

class MyThreadLocal<T> extends ThreadLocal<T> {

    @Override
    protected T initialValue() {
        return (T) "world";
    }
}