package org.saxing.delegation;

/**
 * Interface that both the Controller and the Delegate will implement.
 *
 * @author saxing 2018/12/10 22:20
 */
public interface Printer {

    /**
     * print
     *
     * @param message
     */
    void print(final String message);

}
