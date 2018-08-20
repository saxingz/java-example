package org.saxing.netty.struct;

public final class NettyMessage {

	private NettyMessageHeader nettyMessageHeader;
	
	private Object body;

	public final NettyMessageHeader getNettyMessageHeader() {
		return nettyMessageHeader;
	}

	public final void setNettyMessageHeader(NettyMessageHeader nettyMessageHeader) {
		this.nettyMessageHeader = nettyMessageHeader;
	}

	public final Object getBody() {
		return body;
	}

	public final void setBody(Object body) {
		this.body = body;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
    	return "NettyMessage [nettyMessageHeader=" + nettyMessageHeader + "]";
    }
}
