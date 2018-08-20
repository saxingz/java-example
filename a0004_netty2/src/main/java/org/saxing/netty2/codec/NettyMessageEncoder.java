package org.saxing.netty2.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.saxing.netty2.struct.NettyMessage;
import org.saxing.netty2.struct.NettyMessageHeader;

import java.io.IOException;
import java.util.Map;

public class NettyMessageEncoder  extends MessageToByteEncoder<NettyMessage> {

//	private MarshallingEncoder marshallingEncoder;
	
	public NettyMessageEncoder() throws IOException {
//		this.marshallingEncoder = new MarshallingEncoder();
	}
	
	
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage message, ByteBuf sendBuf) throws Exception {
		if(message == null || message.getNettyMessageHeader() == null){
			throw new Exception("编码失败,没有数据信息!");
		}
		
		//Head:
		NettyMessageHeader nettyMessageHeader = message.getNettyMessageHeader();
		sendBuf.writeInt(nettyMessageHeader.getCrcCode());//校验码
		sendBuf.writeInt(nettyMessageHeader.getLength());//总长度
		sendBuf.writeLong(nettyMessageHeader.getSessionID());//会话id
		sendBuf.writeByte(nettyMessageHeader.getType());//消息类型
		sendBuf.writeByte(nettyMessageHeader.getPriority());//优先级


		//Body:
		byte[] body = message.getBody();
		//如果不为空 说明: 有数据
		if(body != null){
			//使用MarshallingEncoder
			int bodyLength = body.length;
			sendBuf.writeInt(bodyLength);
			sendBuf.writeBytes(body);
//			this.marshallingEncoder.encode(body, sendBuf);


		} else {
			//如果没有数据 则进行补位 为了方便后续的 decoder操作
			sendBuf.writeInt(0);
		}
		
		//最后我们要获取整个数据包的总长度 也就是 nettyMessageHeader +  body 进行对 nettyMessageHeader length的设置
		
		// TODO:  解释： 在这里必须要-8个字节 ，是因为要把CRC和长度本身占的减掉了
		//（官方中给出的是：LengthFieldBasedFrameDecoder中的lengthFieldOffset+lengthFieldLength）
		//总长度是在header协议的第二个标记字段中
		//第一个参数是长度属性的索引位置
		sendBuf.setInt(4, sendBuf.readableBytes() - 8);
		
		
		
		
	}

}
