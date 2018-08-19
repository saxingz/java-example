package org.saxing.multithread.a20180814.future3;

public class Host {

    public Data request(final int count, final char c){
        System.out.println("  request (" + count + ", " + c + " ) BEGIN");

        final FutureData future = new FutureData();

        new Thread(){
            @Override
            public void run() {
                try {
                    RealData realData = new RealData(count, c);
                    future.setRealData(realData);
                }catch (Exception e){
                    future.setException(e);
                }

            }
        }.start();

        System.out.println("  request (" + count + ", " + c + " ) END");
        return future;
    }

}
