package org.saxing.eventasynchronous;

/**
 * Type of Exception raised when the Operation being invoked is Invalid
 *
 * @author saxing 2018/12/24 23:17
 */
public class InvalidOperationException extends Exception {
    private static final long serialVersionUID = -4473797487160871347L;

    public InvalidOperationException(String message) {
        super(message);
    }
}
