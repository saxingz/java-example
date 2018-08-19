package org.saxing.multithread.a20180813.balk_host;

import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {
        Host host = new Host(10000);
        try {
            System.out.println("execute start");
            host.execute();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
