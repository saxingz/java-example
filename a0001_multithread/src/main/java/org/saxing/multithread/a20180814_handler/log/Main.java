package org.saxing.multithread.a20180814_handler.log;


import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

class Log{
    public static void println(String s){
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}

public class Main {

    public static void main(String[] args) {
        Thread.currentThread().setName("MainThread");
        Log.println("main: begin");
        new Executor(){
            @Override
            public void execute(Runnable command) {
                Log.println("execute: begin");
                new ThreadFactory(){
                    @Override
                    public Thread newThread(Runnable r) {
                        Log.println("newThread: begin");
                        Thread t = new Thread(r, "QuizThread");
                        Log.println("newThread: end");
                        return t;
                    }
                }.newThread(command).start();
                Log.println("execute: end");
            }
        }.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        Log.println("run: begin");
                        Log.println("Hello !");
                        Log.println("run: end");
                    }
                }
        );
        Log.println("main: end");
    }

}
