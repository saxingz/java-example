package org.saxing.dao;

/**
 * Custom exception
 *
 * @author saxing 2018/12/2 21:02
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = -5041622097794754516L;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
