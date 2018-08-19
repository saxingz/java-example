package org.saxing.multithread.a20180815_netty.netty;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ClientSocket {
//    private static final Logger logger = Logger.getLogger(ClientSocket.class);
    private static final String HOST = "10.250.251.164";
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
