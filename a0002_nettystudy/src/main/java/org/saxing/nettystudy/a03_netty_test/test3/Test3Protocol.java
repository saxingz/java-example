package org.saxing.nettystudy.a03_netty_test.test3;

import java.util.Arrays;

public class Test3Protocol {

    private int headData = ConstantValue.HEAD_DATA;

    private int contentLength;

    private int mediaId;

    private byte[] content;

    public Test3Protocol(int contentLength, int mediaId, byte[] content) {
        this.contentLength = contentLength;
        this.mediaId = mediaId;
        this.content = content;
    }

    public int getContentLength() {
        return contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public int getHeadData() {
        return headData;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Test3Protocol{" +
                "headData=" + headData +
                ", contentLength=" + contentLength +
                ", mediaId=" + mediaId +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }
}
