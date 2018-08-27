package org.saxing.netty_udp.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.logging.log4j.util.Chars;
import org.saxing.netty_udp.UdpHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.group(nioEventLoopGroup);
        bootstrap.handler(new ChannelInitializer<DatagramChannel>() {

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
            }

            @Override
            protected void initChannel(DatagramChannel datagramChannel) throws Exception {
                ChannelPipeline cp = datagramChannel.pipeline();
                cp.addLast("framer", new MessageToMessageDecoder<DatagramPacket>() {
                    @Override
                    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {

                    }
                }).addLast("handler", new UdpHandler());
            }
        });

        ChannelFuture sync = bootstrap.bind(0).sync();
        Channel udpChannel = sync.channel();

        String data = "我是一匹狼";
        udpChannel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(data.getBytes(Charset.forName("UTF-8"))), new InetSocketAddress("127.0.0.1", 9099)));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                nioEventLoopGroup.shutdownGracefully();
            }
        }));

    }

}
