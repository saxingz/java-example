package org.saxing.netty2.struct;

public final class NettyMessage {

	private NettyMessageHeader nettyMessageHeader;
	
	private byte[] body;

	public final NettyMessageHeader getNettyMessageHeader() {
		return nettyMessageHeader;
	}

	public final void setNettyMessageHeader(NettyMessageHeader nettyMessageHeader) {
		this.nettyMessageHeader = nettyMessageHeader;
	}

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
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
