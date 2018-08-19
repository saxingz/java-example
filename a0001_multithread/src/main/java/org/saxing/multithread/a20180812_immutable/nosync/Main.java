package org.saxing.multithread.a20180812_immutable.nosync;

public class Main {

    private static final long CALL_COONT = 1000000000L;

    public static void main(String[] args) {
        trial("NotSync", CALL_COONT, new NotSync());
        trial("NotSync", CALL_COONT, new Synch());
    }

    private static void trial(String msg, long count, Object obj){
        System.out.println(msg + ": Begin");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            obj.toString();
        }
        System.out.println(msg + ": End");
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - startTime) + "msec.");
    }

}


class NotSync{
    private final String name = "NotSync";

    @Override
    public String toString() {
        return "NotSync{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Synch{
    private final String name = "Synch";

    @Override
    public synchronized String toString() {
        return "Synch{" +
                "name='" + name + '\'' +
                '}';
    }
}


