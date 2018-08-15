package org.saxing.a001_multithread.a20180813.balk;

public class Main {

    public static void main(String[] args) {
        Data data = new Data("data.txt", "(empty)");
        new ChangerThread("ChangerThread", data).start();
        new SaverThread("saverThread", data).start();
    }

}
