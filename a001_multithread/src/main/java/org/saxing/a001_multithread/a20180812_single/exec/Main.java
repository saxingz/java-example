package org.saxing.a001_multithread.a20180812_single.exec;

public class Main {

    public static void main(String[] args) {
        System.out.println("Testing...");
        Tool spoon = new Tool("Spoon");
        Tool fork = new Tool("Fork");
        new EaterThread("Alice", fork, spoon).start();
        new EaterThread("Bobby", spoon, fork).start();
    }


}
