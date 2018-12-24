package org.saxing.eventasynchronous;

/**
 * Type of Exception raised when the max number of allowed events is exceeded
 *
 * @author saxing 2018/12/24 23:18
 */
public class MaxNumOfEventsAllowedException extends Exception {
    private static final long serialVersionUID = -3203726238909408142L;

    public MaxNumOfEventsAllowedException(String message) {
        super(message);
    }
}
