package org.saxing.callback;

/**
 * Template-method class for callback hook execution
 *
 * @author saxing  2018/11/17 11:23
 */
public abstract class Task {

    public final void executeWith(Callback callback){
        execute();
        if (callback != null){
            callback.call();
        }
    }

    public abstract void execute();

}
