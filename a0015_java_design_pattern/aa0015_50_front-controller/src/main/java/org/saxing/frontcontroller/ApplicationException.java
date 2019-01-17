package org.saxing.frontcontroller;

/**
 * Custom exception type
 *
 * @author saxing 2019/1/17 22:54
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -8337816844994998103L;

    public ApplicationException(Throwable cause) {
        super(cause);
    }

}
