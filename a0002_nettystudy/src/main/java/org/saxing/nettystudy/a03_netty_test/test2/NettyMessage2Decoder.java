package org.saxing.nettystudy.a03_netty_test.test2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class NettyMessage2Decoder extends LengthFieldBasedFrameDecoder {


    public NettyMessage2Decoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }

    public NettyMessage2Decoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive触发。。。");
        super.channelActive(ctx);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf bf = null;
        NettyMessage2 nm = null;
        try {
            bf = (ByteBuf) super.decode(ctx, in);
            nm = new NettyMessage2();
            if (nm == null){
                return null;
            }
            nm.setOpCode(bf.readInt());
            nm.setPlayerId(bf.readInt());
            int size = bf.readInt();
            if (size > 0){
                List<String> list = new ArrayList<>();
                while (size > 0){
                    int ssize = bf.readShort();
                    byte[] value = new byte[ssize];
                    bf.readBytes(value);
                    list.add(new String(value, "utf-8"));
                    value = null;
                    size -= 1;
                }
            }
        }finally {
            if (in != null){
                in.release();
            }
        }

        return nm;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" channelInactive    客户端断开，TCP连接关闭");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught....");
        super.exceptionCaught(ctx, cause);
    }
}
