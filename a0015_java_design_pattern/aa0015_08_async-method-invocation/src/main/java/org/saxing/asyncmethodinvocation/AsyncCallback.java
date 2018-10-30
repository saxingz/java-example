package org.saxing.asyncmethodinvocation;

import java.util.Optional;

/**
 * async callback interface
 * 
 * @param <T>
 *     
 * @author saxing  2018/10/30 9:20
 */
public interface AsyncCallback<T> {

    /**
     * Complete handler which is executed when async task is completed or fails execution.
     *
     * @param value
     * @param e
     */
    void onComplete(T value, Optional<Exception> e);
}
