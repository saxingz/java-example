package org.saxing.d_queue.aio2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * client
 *
 * @author saxing 2020/5/7 20:17
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("准备开始。。。。。。。。");
        new Thread(() -> connect()).start();
        new Thread(() -> connect()).start();
        new Thread(() -> connect()).start();
    }

    public static void connect() {
        AsynchronousSocketChannel socketChannel = null;
        try {
            socketChannel = AsynchronousSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 80);
            Future<Void> connect = socketChannel.connect(inetSocketAddress);

            while (!connect.isDone()) {
                Thread.sleep(10);
            }

            System.out.println("建立连接" + socketChannel.getRemoteAddress());

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Future<Integer> read = socketChannel.read(buffer);

            while (!read.isDone()) {
                Thread.sleep(10);
            }

            System.out.println("接收服务器数据:" + new String(buffer.array(), 0, read.get()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
