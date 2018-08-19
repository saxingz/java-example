package org.saxing.a001_multithread.a20180815_netty.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Test4Encoder extends MessageToByteEncoder<Test4Protocol> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Test4Protocol test4Protocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(test4Protocol.getHeadData());
        byteBuf.writeInt(test4Protocol.getContentLength());
        byteBuf.writeInt(test4Protocol.getMediaId());
        byteBuf.writeBytes(test4Protocol.getContent());
    }
}
