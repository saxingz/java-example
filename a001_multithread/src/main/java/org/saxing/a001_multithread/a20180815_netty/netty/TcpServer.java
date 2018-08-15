package org.saxing.a001_multithread.a20180815_netty.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.apache.log4j.PropertyConfigurator;

public class TcpServer {

//    private static final Logger logger = Logger.getLogger(TcpServer.class);
    private static final String IP = "127.0.0.1";
    private static final int PORT = 9999;

    //默认
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;

    protected static final int BIZTHREADSIZE = 4;

    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    protected static void run() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
                        Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new SocketByteHandler());
            }
        });

        b.bind(IP, PORT).sync();
//        logger.info("TCP服务器已启动");
        System.out.println("TCP服务器已启动");
    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("a001_multithread/log/log4j.properties");
//        logger.info("开始启动TCP服务器...");
        System.out.println("开始启动TCP服务器...");
        TcpServer.run();
//      TcpServer.shutdown();
    }

}
