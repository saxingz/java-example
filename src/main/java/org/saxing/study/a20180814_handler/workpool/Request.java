package org.saxing.study.a20180814_handler.workpool;

import java.util.Random;

public class Request {

    private final String name;
    private final int number;
    private static final Random random = new Random();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return "Request from {" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
