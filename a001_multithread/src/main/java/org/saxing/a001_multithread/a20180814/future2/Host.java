package org.saxing.a001_multithread.a20180814.future2;

import java.util.concurrent.Callable;

public class Host {

    public Data request(final int count, final char c){
        System.out.println("  request (" + count + ", " + c + " ) BEGIN");

        final FutureData future = new FutureData(
                new Callable<RealData>() {
                    @Override
                    public RealData call() throws Exception {
                        return new RealData(count, c);
                    }
                }
        );

        new Thread(future).start();

        System.out.println("  request (" + count + ", " + c + " ) END");
        return future;
    }

}
