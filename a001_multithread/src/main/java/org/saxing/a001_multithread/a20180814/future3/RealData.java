package org.saxing.a001_multithread.a20180814.future3;

public class RealData implements Data {

    private final String content;

    public RealData(int count, char c) {
        System.out.println("  making realdata (" + count + ", " + c + " ) start");
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }

        System.out.println("  making realdata (" + count + ", " + c + " ) end");
        this.content = new String(buffer);
    }

    @Override
    public String getContent() {
        return content;
    }
}
