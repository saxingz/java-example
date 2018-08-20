package org.saxing.netty2.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.saxing.netty2.struct.NettyMessage;
import org.saxing.netty2.struct.NettyMessageHeader;

import java.io.IOException;

/**
 * LengthFieldBasedFrameDecoder 是为了解决 拆包粘包等问题的
 *
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

//	private MarshallingDecoder marshallingDecoder;
	
	/**
	 * 那减8应该是因为要把CRC和长度本身占的减掉了。
	 * @param maxFrameLength 第一个参数代表最大的序列化长度   1024*1024*5
	 * @param lengthFieldOffset 代表长度属性的偏移量 简单来说就是message中 总长度的起始位置（Header中的length属性的起始位置）   本例中为4
	 * @param lengthFieldLength 代表长度属性的长度 整个属性占多长（length属性为int，占4个字节）  4
	 * @throws IOException 
	 */
	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
//		this.marshallingDecoder = new MarshallingDecoder();
	}
	
	@Override
	protected Object decode(ChannelHandlerContext ctx,  ByteBuf in) throws Exception {
		//1 调用父类(LengthFieldBasedFrameDecoder)方法:
		ByteBuf frame  = (ByteBuf)super.decode(ctx, in);
		
		if(frame == null){
			return null;
		}
		
		NettyMessage message = new NettyMessage();
		NettyMessageHeader nettyMessageHeader = new NettyMessageHeader();
		nettyMessageHeader.setCrcCode(frame.readInt());		//crcCode ----> 添加通信标记认证逻辑
		nettyMessageHeader.setLength(frame.readInt());		//length
		nettyMessageHeader.setSessionID(frame.readLong());	//sessionID
		nettyMessageHeader.setType(frame.readByte());		//type
		nettyMessageHeader.setPriority(frame.readByte());	//priority

		message.setNettyMessageHeader(nettyMessageHeader);
		//对于ByteBuf来说，读一个数据，就会少一个数据，所以读完header，剩下的应该就是body了
		if(frame.readableBytes() > 4) { //大于4个字节，肯定就有数据了（4个字节是内容长度的占位）
			int bodyLength = frame.readInt();
			ByteBuf byteBuf = frame.readBytes(bodyLength);
			byte[] bodyBytes = new byte[bodyLength];
			byteBuf.getBytes(0, bodyBytes);
			message.setBody(bodyBytes);
		}
		return message;
	}
}
