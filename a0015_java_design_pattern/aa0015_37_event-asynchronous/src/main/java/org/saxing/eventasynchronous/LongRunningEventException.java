package org.saxing.eventasynchronous;

/**
 * Type of Exception raised when the Operation being invoked is Long Running
 *
 * @author saxing 2018/12/24 23:17
 */
public class LongRunningEventException extends Exception {
    private static final long serialVersionUID = -5653649725966610942L;

    public LongRunningEventException(String message) {
        super(message);
    }
}
