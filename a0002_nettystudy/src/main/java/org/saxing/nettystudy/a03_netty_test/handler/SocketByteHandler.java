package org.saxing.nettystudy.a03_netty_test.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SocketByteHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg)
//            throws Exception {
//         super.channelRead(ctx, msg);
//        MyProtocolBean result = (MyProtocolBean) msg;
//        result.getContent();
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        result.readBytes(result1);
//
//        exportFile(result1, "d://", "outfile.png");
//
//        // 释放资源，很关键
//        result.release();
//        String response = "I am ok!";
//        // 在当前场景下，发送的数据必须转换成ByteBuf数组
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
//        ctx.flush();
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // super.channelRead(ctx, msg);
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(result1);

        exportFile(result1, "d://", "22.pdf");

        // 释放资源，很关键
        result.release();
        String response = "I am ok!";
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void exportFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg)
//            throws Exception {
//        // super.channelRead(ctx, msg);
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        result.readBytes(result1);
//        String resultStr = new String(result1);
//        System.out.println("Client said:" + resultStr);
//        // 释放资源，很关键
//        result.release();
//        String response = "I am ok!";
//        // 在当前场景下，发送的数据必须转换成ByteBuf数组
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
//        ctx.flush();
//    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }

}
