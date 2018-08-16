package org.saxing.a001_multithread.a20180815_netty.netty;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ClientSocket {
//    private static final Logger logger = Logger.getLogger(ClientSocket.class);
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9999;

    public static void main(String[] args) {
        String str = "abcdefg";
        sendMsgBySocket(str.getBytes());
    }

    public static void sendMsgBySocket(byte[] msg){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(HOST,PORT));
            socket.setKeepAlive(true);
            OutputStream out = socket.getOutputStream();
            ByteBuffer header = ByteBuffer.allocate(4);
            header.putInt(msg.length);
            out.write(header.array());
            out.write(msg);
            out.flush();
            InputStream in = socket.getInputStream();
            byte[] buff = new byte[4096];
            int readed = in.read(buff);
            if(readed > 0){
                String str = new String(buff,4,readed);
//                logger.info("client received msg from server:"+str);
                System.out.println("client received msg from server:"+str);
            }
            out.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
