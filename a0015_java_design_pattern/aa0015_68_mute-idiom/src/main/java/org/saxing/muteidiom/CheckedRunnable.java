package org.saxing.muteidiom;

/**
 * A runnable which may throw exception on execution.
 *
 * @author saxing 2019/4/28 9:39
 */
@FunctionalInterface
public interface CheckedRunnable {

    /**
     * Same as {@link Runnable#run()} with a possibility of exception in execution.
     * @throws Exception if any exception occurs.
     */
    void run() throws Exception;

}
