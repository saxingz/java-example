package org.saxing.d_queue.aio;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * client
 *
 * @author saxing 2020/5/6 23:31
 */
public class Client implements Runnable {

    private AsynchronousSocketChannel asc ;

    public Client() throws Exception {
        asc = AsynchronousSocketChannel.open();
    }

    public void connect(){
        asc.connect(new InetSocketAddress("127.0.0.1", 8765));
    }

    public void write(String request){
        try {
            asc.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        try {
            asc.read(buf).get();
            buf.flip();
            byte[] respByte = new byte[buf.remaining()];
            buf.get(respByte);
            System.out.println(new String(respByte,"utf-8").trim());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws Exception {

//        for (int i = 0; i < 100; i++) {
//            Client c = new Client();
//            c.connect();
//
//
//        }

        Client c1 = new Client();
        c1.connect();
        Client c2 = new Client();
        c2.connect();
        Client c3 = new Client();
        c3.connect();
        Client c4 = new Client();
        c4.connect();
        Client c5 = new Client();
        c5.connect();
        Client c6 = new Client();
        c6.connect();
        Client c7 = new Client();
        c7.connect();
        Client c8 = new Client();
        c8.connect();
        Client c9 = new Client();
        c9.connect();

//        new Thread(c1, "c1").start();
//        new Thread(c2, "c2").start();
//        new Thread(c3, "c3").start();

        Thread.sleep(1000);



        new Thread(() -> {
            c1.write("c1 1");
        }).start();
        new Thread(() -> {
            c2.write("c2 22");
        }).start();
        new Thread(() -> {
            c3.write("c3 333");
        }).start();
        new Thread(() -> {
            c4.write("c4 4444");
        }).start();
        new Thread(() -> {
            c5.write("c 55555");
        }).start();
        new Thread(() -> {
            c6.write("c 666666");
        }).start();
        new Thread(() -> {
            c7.write("c 7777777");
        }).start();
        new Thread(() -> {
            c8.write("c 88888888");
        }).start();
        new Thread(() -> {
            c9.write("c 999999999");
        }).start();
    }

}
