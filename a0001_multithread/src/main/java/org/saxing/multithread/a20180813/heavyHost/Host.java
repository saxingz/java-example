package org.saxing.multithread.a20180813.heavyHost;

public class Host {

    public static void execute(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            if (Thread.interrupted()){
                throw new InterruptedException();
            }
            doHeaveJob();
        }
    }

    private static void doHeaveJob() {
        System.out.println("do heavy job  begin...");
        long start = System.currentTimeMillis();
        while (start + 10000 > System.currentTimeMillis()){

        }
        System.out.println("do heavy job end.");
    }

}
