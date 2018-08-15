package org.saxing.a001_multithread.a20180815_netty.activeObject_concurrent;

public class Main {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObjectFactory();
        try {
            new MakerClientThread("Alice", activeObject).start();
            new MakerClientThread("Bobby", activeObject).start();
            new DisplayClientThread("Chris", activeObject).start();
            Thread.sleep(50000);
        } catch (InterruptedException e) {
        } finally {
            System.out.println(" *** shutdown ***");
            activeObject.shutdown();
        }
    }

}
