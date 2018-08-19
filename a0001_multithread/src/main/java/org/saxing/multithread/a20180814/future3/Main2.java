package org.saxing.multithread.a20180814.future3;

import java.util.concurrent.ExecutionException;

public class Main2 {

    public static void main(String[] args) throws ExecutionException {
        Host host = new Host();

        Data data = host.request(-1, 'N');

        System.out.println(data.getContent());


    }

}
