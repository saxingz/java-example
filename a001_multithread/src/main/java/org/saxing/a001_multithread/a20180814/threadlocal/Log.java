package org.saxing.a001_multithread.a20180814.threadlocal;

public class Log {

    private static final ThreadLocal<TSLog> tsLogCollection = new ThreadLocal<>();

    public static void println(String s){
        getTslog().println(s);
    }

    public static void close(){
        getTslog().close();
    }

    private static TSLog getTslog(){
        TSLog tsLog = tsLogCollection.get();
        if (tsLog == null){
            tsLog = new TSLog(Thread.currentThread().getName() + "-log.txt");
            tsLogCollection.set(tsLog);
        }
        return tsLog;
    }

}
