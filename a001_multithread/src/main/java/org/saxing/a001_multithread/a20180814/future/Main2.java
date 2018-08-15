package org.saxing.a001_multithread.a20180814.future;

public class Main2 {

    public static void main(String[] args) {
        Host host = new Host();

        Data data = host.request(-1, 'N');

        System.out.println(data.getContent());


    }

}
