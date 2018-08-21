package org.saxing.nettystudy.a03_netty_test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.saxing.nettystudy.a03_netty_test.test3.ServerHanlder;
import org.saxing.nettystudy.a03_netty_test.test3.Test3Decoder;
import org.saxing.nettystudy.a03_netty_test.test3.Test3Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * netty 服务端
 * 
 * @author 刘罕  2018/8/15 17:09
 */
@Service
public class NettyServer {

    private Logger log = LoggerFactory.getLogger(NettyServer.class);

    //默认
    private static final int BIZ_GROUP_SIZE = Runtime.getRuntime().availableProcessors()*2;
    private static final int BIZ_THREAD_SIZE = 4;

    private static final EventLoopGroup BOSS_GROUP = new NioEventLoopGroup(BIZ_GROUP_SIZE);
    private static final EventLoopGroup WORKER_GROUP = new NioEventLoopGroup(BIZ_THREAD_SIZE);

    /**
     * 端口号
     */
    @Value("${netty.port}")
    private int port;

    /**
     * 服务启动方法
     * 
     * @author 刘罕  2018/8/15 17:15
     */
    @PostConstruct
    public void run(){
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(BOSS_GROUP, WORKER_GROUP);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
//                    pipeline.addLast("frameDecoder", new NettyMessage2Decoder(
//                            2048, 0, 4, 0, 4));
//                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast(new Test3Encoder());
                    pipeline.addLast(new Test3Decoder());
                    pipeline.addLast(new ServerHanlder());
                }
            });

            b.bind(port).sync();
            System.out.println("TCP服务器已启动");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 优雅关闭
     *
     * @author 刘罕  2018/8/16 11:16
     */
    @PreDestroy
    public void shutdown(){
        System.out.println("优雅关闭");
        BOSS_GROUP.shutdownGracefully();
        WORKER_GROUP.shutdownGracefully();
    }


}
