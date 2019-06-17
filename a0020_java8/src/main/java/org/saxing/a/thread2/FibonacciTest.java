package org.saxing.a.thread2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciTest {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(4);
        Fibonacci fib = new Fibonacci(6);
        Integer result = fjp.invoke(fib);
        System.out.println(result);
    }

    static class Fibonacci extends RecursiveTask<Integer>{

        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1){
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            // create sub task
            f1.fork();

            Fibonacci f2 = new Fibonacci(n - 2);

            return f2.compute() + f1.join();
        }
    }
}


