package org.saxing.multithread.a20180813.request3;

public class Main {

    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        Thread alice =  new ClientThread(queue, "Alice", 3141592L);
        Thread bobby = new ServerThread(queue, "Bobby", 6535897L);

        alice.start();
        bobby.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("called interrupted.....");
        alice.interrupt();
        bobby.interrupt();

    }

}
