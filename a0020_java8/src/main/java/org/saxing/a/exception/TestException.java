package org.saxing.a.exception;

import java.io.IOException;

public class TestException {

    public static void main(String[] args) {
        try {
            testIoException();
        } catch (Throwable t) {
            System.out.println(t);
        }
    }

    public static void testIoException() throws IOException {
        throw new IOException("Connection reset by peer");
    }

}
