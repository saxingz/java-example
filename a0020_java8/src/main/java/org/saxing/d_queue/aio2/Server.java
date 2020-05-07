package org.saxing.d_queue.aio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * server
 *
 * @author saxing 2020/5/7 20:16
 */
public class Server {

    public static void main(String[] args) {
        try {
            Server server = new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Server() throws Exception {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 80);
        serverSocketChannel.bind(inetSocketAddress);

        Future<AsynchronousSocketChannel> accept;

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        while (true) {
            // accept()不会阻塞。
            accept = serverSocketChannel.accept();

            System.out.println("=================");
            System.out.println("服务器等待连接...");
            AsynchronousSocketChannel socketChannel = accept.get();// get()方法将阻塞。

            executorService.submit(new ServerRunner(socketChannel));
        }
    }

    class ServerRunner implements Runnable{

        AsynchronousSocketChannel socketChannel;

        public ServerRunner(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            System.out.println("服务器接受连接");
            try {
                System.out.println("服务器与" + socketChannel.getRemoteAddress() + "建立连接");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("处理中。。。。。。。");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ByteBuffer buffer = ByteBuffer.wrap("zhangphil".getBytes());
            Future<Integer> write=socketChannel.write(buffer);

            while(!write.isDone()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("服务器发送数据完毕.");
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
