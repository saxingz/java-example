package org.saxing.netty.struct;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class NettyMessageHeader {

	private int crcCode = 0xADAF0105; // 唯一的通信标志

	private int length; // 总消息的长度 header + body

	private long sessionID; // 会话ID

	/**
	 * @see  org.saxing.netty.MessageType
	 */
	private byte type; // 消息的类型 

	private byte priority; // 消息的优先级 0~255

	private Map<String, Object> attachment = new HashMap<String, Object>(); // 附件
	// ...

	public int getCrcCode() {
		return crcCode;
	}

	public void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getSessionID() {
		return sessionID;
	}

	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}
	
	public Map<String, Object> getAttachment() {
		return attachment;
	}

	public void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
    	return "NettyMessageHeader [crcCode=" + crcCode + ", length=" + length
		+ ", sessionID=" + sessionID + ", type=" + type + ", priority="
		+ priority + ", attachment=" + attachment + "]";
    }
}
