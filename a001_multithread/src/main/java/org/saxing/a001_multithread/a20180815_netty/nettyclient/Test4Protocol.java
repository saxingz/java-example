package org.saxing.a001_multithread.a20180815_netty.nettyclient;

import java.util.Arrays;

public class Test4Protocol {

    private int headData = ConstantValue.HEAD_DATA;

    private int contentLength;

    private int mediaId;

    private byte[] content;

    public Test4Protocol(int contentLength, int mediaId, byte[] content) {
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
        return "Test4Protocol{" +
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
