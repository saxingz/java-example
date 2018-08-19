package org.saxing.multithread.a20180815_netty.activeObject;

import org.saxing.multithread.a20180815_netty.activeObject.activeobject.Servant;
import org.saxing.multithread.a20180815_netty.activeObject.result.FutureResult;

abstract class MethodRequest<T> {

    public final Servant servant;
    public final FutureResult<T> future;

    public MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    public abstract void execute();
}
