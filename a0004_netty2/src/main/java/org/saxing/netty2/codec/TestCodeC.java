/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.saxing.netty2.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.saxing.netty2.struct.NettyMessage;
import org.saxing.netty2.struct.NettyMessageHeader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class TestCodeC {

	MarshallingEncoder marshallingEncoder;
	MarshallingDecoder marshallingDecoder;

	public TestCodeC() throws IOException {
		marshallingDecoder = new MarshallingDecoder();
		marshallingEncoder = new MarshallingEncoder();
	}

	public NettyMessage getMessage() {
		NettyMessage nettyMessage = new NettyMessage();
		NettyMessageHeader nettyMessageHeader = new NettyMessageHeader();
		nettyMessageHeader.setLength(123);
		nettyMessageHeader.setSessionID(99999);
		nettyMessageHeader.setType((byte) 1);
		nettyMessageHeader.setPriority((byte) 7);
		nettyMessage.setNettyMessageHeader(nettyMessageHeader);
		nettyMessage.setBody("abcdefg-----------------------AAAAAA".getBytes());
		return nettyMessage;
	}

	public ByteBuf encode(NettyMessage msg) throws Exception {
		ByteBuf sendBuf = Unpooled.buffer();
		sendBuf.writeInt((msg.getNettyMessageHeader().getCrcCode()));
		sendBuf.writeInt((msg.getNettyMessageHeader().getLength()));
		sendBuf.writeLong((msg.getNettyMessageHeader().getSessionID()));
		sendBuf.writeByte((msg.getNettyMessageHeader().getType()));
		sendBuf.writeByte((msg.getNettyMessageHeader().getPriority()));

		if (msg.getBody() != null) {
			marshallingEncoder.encode(msg.getBody(), sendBuf);
		} else {
			sendBuf.writeInt(0);
		}
		sendBuf.setInt(4, sendBuf.readableBytes());
		return sendBuf;
	}

	public NettyMessage decode(ByteBuf in) throws Exception {
		NettyMessage message = new NettyMessage();
		NettyMessageHeader nettyMessageHeader = new NettyMessageHeader();
		nettyMessageHeader.setCrcCode(in.readInt());
		nettyMessageHeader.setLength(in.readInt());
		nettyMessageHeader.setSessionID(in.readLong());
		nettyMessageHeader.setType(in.readByte());
		nettyMessageHeader.setPriority(in.readByte());


		if (in.readableBytes() > 4) {
			message.setBody((byte[]) marshallingDecoder.decode(in));
		}
		message.setNettyMessageHeader(nettyMessageHeader);
		return message;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TestCodeC testC = new TestCodeC();
		NettyMessage message = testC.getMessage();
		System.out.println(message + "[body ] " + message.getBody());

		for (int i = 0; i < 5; i++) {
			ByteBuf buf = testC.encode(message);
			NettyMessage decodeMsg = testC.decode(buf);
			System.out.println(decodeMsg + "[body ] " + decodeMsg.getBody());
			System.out.println("-------------------------------------------------");

		}

	}

}
