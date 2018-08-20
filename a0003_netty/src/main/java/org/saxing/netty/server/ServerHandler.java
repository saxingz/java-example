package org.saxing.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.saxing.netty.struct.NettyMessageHeader;
import org.saxing.netty.struct.NettyMessage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 当我们通道进行激活的时候 触发的监听方法
	 */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    
    	System.err.println("--------通道激活------------");
    }
	
    /**
     * 当我们的通道里有数据进行读取的时候 触发的监听方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx /*NETTY服务上下文*/, Object msg /*实际的传输数据*/) throws Exception {
    	
    	NettyMessage requestMessage = (NettyMessage)msg;
    	
//    	System.err.println("Server receive message from Client: " + requestMessage.getBody());

		byte[] bytes = (byte[]) requestMessage.getBody();

		exportFile(bytes, "e:\\temp\\", "2.pdf");

    	NettyMessage responseMessage = new NettyMessage();
		NettyMessageHeader nettyMessageHeader = new NettyMessageHeader();
		nettyMessageHeader.setSessionID(2002L);
		nettyMessageHeader.setPriority((byte)2);
		nettyMessageHeader.setType((byte)1);
		responseMessage.setNettyMessageHeader(nettyMessageHeader);
//		responseMessage.setBody("我是响应数据: " + requestMessage.getBody());
		responseMessage.setBody("我是响应数据: 123");
		ctx.writeAndFlush(responseMessage);
    	
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       System.err.println("--------数据读取完毕----------");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
    	System.err.println("--------服务器数据读异常----------: ");
    	cause.printStackTrace();
        ctx.close();
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
    
    
    
    
    
    
    
    
}
