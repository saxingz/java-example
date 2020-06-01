package org.saxing.java8;

public class ExceptionTest {

    static class ChildThread implements Runnable {
        ChildThreadExceptionHandler exceptionHandler;


        public ChildThread(ChildThreadExceptionHandler exceptionHandler
                           ) {
            this.exceptionHandler = exceptionHandler;

        }

        @Override
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(exceptionHandler);
            testEx();
        }

        private Integer testEx() {
            return  1 / 0;
        }
    }

    static class ChildThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ChildThread thread = new ChildThread(new ChildThreadExceptionHandler());
        new Thread(thread).start();

        new Thread(){
            @Override
            public void run() {

                int i = 1/ 0;
                System.out.println(i);
            }
        }.start();
    }

}
