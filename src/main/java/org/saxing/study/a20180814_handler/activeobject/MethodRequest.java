package org.saxing.study.a20180814_handler.activeobject;

abstract class MethodRequest<T> {

    protected final Servant servant;
    protected final FutureResult<T> future;

    public MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    public abstract void execute();
}
