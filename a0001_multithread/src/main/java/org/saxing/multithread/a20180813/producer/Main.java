package org.saxing.multithread.a20180813.producer;

public class Main {

    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("MakerThread-0", table, 1000).start();
        new MakerThread("MakerThread-1", table, 1001).start();
        new MakerThread("MakerThread-2", table, 1002).start();
        new EaterThread("EaterThread-0", table, 2000).start();
        new EaterThread("EaterThread-1", table, 2001).start();
        new EaterThread("EaterThread-2", table, 2002).start();
    }

}
