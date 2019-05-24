package org.saxing.a.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class AIOServer extends Thread {

    @Override
    public void run() {
        try (AsynchronousServerSocketChannel serverSocketChannel =
                     AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888))) {

            serverSocketChannel.accept(serverSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                @Override
                public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                    serverSocketChannel.accept(serverSocketChannel, this);
                }

                @Override
                public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                    // 另外一个 write（sock，CompletionHandler{}）
//                    sayHelloWorld(serverSocketChannel, Charset.defaultCharset().encode
//                            ("Hello World!"));
                }
            });

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept()) {
            client.write(Charset.defaultCharset().encode("Hello world!"));
        }
    }
}
