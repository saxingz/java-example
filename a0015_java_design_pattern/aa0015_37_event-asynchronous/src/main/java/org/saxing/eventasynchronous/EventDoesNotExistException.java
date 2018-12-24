package org.saxing.eventasynchronous;

/**
 * Custom Exception Class for Non Existent Event
 *
 * @author saxing 2018/12/24 23:15
 */
public class EventDoesNotExistException extends Exception {
    private static final long serialVersionUID = 3515561006700882497L;

    public EventDoesNotExistException(String message) {
        super(message);
    }

}
