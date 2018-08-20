package org.saxing.netty2;

/**
 */
public enum ResultType {
	/**
	 * 认证成功
	 */
	SUCCESS((byte) 0),
	/**
	 * 认证失败
	 */
	FAIL((byte) -1), 

	;
	
	private byte value;

	private ResultType(byte value) {
		this.value = value;
	}

	public byte value() {
		return this.value;
	}
}
