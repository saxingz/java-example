package org.saxing.nettystudy.a03_netty_test.test3.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.saxing.nettystudy.a03_netty_test.test3.Test3Protocol;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    // 客户端与服务端，连接成功的售后
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送test3协议的消息
        // 要发送的信息
        String data = "I am client ...";
        // 获得要发送信息的字节数组
        byte[] content = data.getBytes();
        // 要发送信息的长度
        int contentLength = content.length;
        int mediaId = 123456789;
        Test3Protocol protocol = new Test3Protocol(contentLength, mediaId, content);
        ctx.writeAndFlush(protocol);
    }

    // 只是读数据，没有写数据的话
    // 需要自己手动的释放的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            // 用于获取客户端发来的数据信息
            Test3Protocol body = (Test3Protocol) msg;
            System.out.println("Client接受的客户端的信息 :" + body.toString());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }

}
