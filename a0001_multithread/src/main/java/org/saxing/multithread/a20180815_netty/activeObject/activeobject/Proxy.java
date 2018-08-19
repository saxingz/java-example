package org.saxing.multithread.a20180815_netty.activeObject.activeobject;

import org.saxing.multithread.a20180815_netty.activeObject.DisplayStringRequest;
import org.saxing.multithread.a20180815_netty.activeObject.MakeStringRequest;
import org.saxing.multithread.a20180815_netty.activeObject.SchedulerThread;
import org.saxing.multithread.a20180815_netty.activeObject.result.FutureResult;
import org.saxing.multithread.a20180815_netty.activeObject.result.Result;

public class Proxy implements ActiveObject {

    private final SchedulerThread scheduler;
    private final Servant servant;

    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }

    @Override
    public Result<String> makeString(int count, char filter) {
        FutureResult<String> future = new FutureResult<>();
        scheduler.invoke(new MakeStringRequest(servant, future, count, filter));
        return future;
    }

    @Override
    public void displayString(String string) {
        scheduler.invoke(new DisplayStringRequest(servant, string));
    }
}
