package org.saxing.nettystudy.a03_netty_test.test3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Test3Encoder extends MessageToByteEncoder<Test3Protocol> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Test3Protocol test3Protocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(test3Protocol.getHeadData());
        byteBuf.writeInt(test3Protocol.getContentLength());
        byteBuf.writeInt(test3Protocol.getMediaId());
        byteBuf.writeBytes(test3Protocol.getContent());
    }
}
