package org.saxing.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.saxing.netty.NettyConstant;
import org.saxing.netty.codec.NettyMessageDecoder;
import org.saxing.netty.codec.NettyMessageEncoder;
import org.saxing.netty.struct.NettyMessageHeader;
import org.saxing.netty.struct.NettyMessage;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) throws Exception {
		new Client().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
	}

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	//创建工作线程组
	EventLoopGroup group = new NioEventLoopGroup();

	public void connect(int port, String host) throws Exception {
		// 配置客户端NIO线程组
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
							ch.pipeline().addLast(new NettyMessageEncoder());
							ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
							ch.pipeline().addLast("LoginAuthHandler", new LoginAuthReqHandler());
							ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());
						}
					});
			// 发起异步连接操作
			ChannelFuture future = b.connect(new InetSocketAddress(host, port),
					new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
			
//			手动发测试数据，验证是否会产生TCP粘包/拆包情况
			Channel c = future.channel();

//			for (int i = 0; i < 500; i++) {
				NettyMessage message = new NettyMessage();
				NettyMessageHeader nettyMessageHeader = new NettyMessageHeader();
				nettyMessageHeader.setSessionID(1001L);
				nettyMessageHeader.setPriority((byte) 1);
				nettyMessageHeader.setType((byte) 0);
				message.setNettyMessageHeader(nettyMessageHeader);
				File file = new File("E:\\temp\\1.pdf");
//				message.setBody("我是请求数据" + i);
				message.setBody(toByteArray(file));
				c.writeAndFlush(message);
//			}
			
			future.channel().closeFuture().sync();
		} finally {
			// 所有资源释放完成之后，清空资源，再次发起重连操作
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
						try {
							connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
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
