package org.saxing.study.a20180814_handler.workpool;

public class Main {

    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();
        ClientThread alice =  new ClientThread("alice", channel);
        ClientThread bobby =  new ClientThread("bobby", channel);
        ClientThread chris =  new ClientThread("chris", channel);
        alice.start();
        bobby.start();
        chris.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alice.stopThread();
        bobby.stopThread();
        chris.stopThread();
        channel.stopAllWorkers();
    }

}
