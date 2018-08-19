package org.saxing.a001_multithread.a20180815_netty.netty2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ClientSocket {
//    private static final Logger logger = Logger.getLogger(ClientSocket.class);
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        String str = "abcdefg";
        File file = new File("D:\\satemp\\11.png");
        byte[] pngFile =  toByteArray(file);
//        sendMsgBySocket(pngFile);
        sendMsgBySocket(str.getBytes());
    }

    public static void sendMsgBySocket(byte[] msg){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(HOST,PORT));
            socket.setKeepAlive(true);
            OutputStream out = socket.getOutputStream();
//
            ByteBuffer headData = ByteBuffer.allocate(4);
            headData.putInt(0X76);

            ByteBuffer header = ByteBuffer.allocate(4);
            header.putInt(msg.length);
//
            ByteBuffer mediaId = ByteBuffer.allocate(4);
            mediaId.putInt(1234567890);

//            out.write(headData.array());
//            out.write(header.array());
//            out.write(mediaId.array());
//            out.write(msg);

            out.write(0X76);
            out.write(msg.length);
            out.write(12345678);
            out.write(msg);

//            // 要发送的信息
//            String data = "I am client ...";
//            // 获得要发送信息的字节数组
//            byte[] content = data.getBytes();
//            // 要发送信息的长度
//            int contentLength = content.length;
//            int mediaId2 = 123456789;
//            Test4Protocol test3Protocol = new Test4Protocol(contentLength, mediaId2, content);
//
//            byte[] bytes = new byte[1024];
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            ObjectOutputStream oo = new ObjectOutputStream(bo);
//            oo.writeObject(test3Protocol);
//            bytes = bo.toByteArray();
//            out.write(bytes);

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


    /*读取文件的字节数组*/
    public static byte[] toByteArray(File file) throws IOException {
        File f = file;
        if (!f.exists()) {
            throw new FileNotFoundException("file not exists");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

}
