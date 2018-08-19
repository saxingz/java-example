package org.saxing.multithread.a20180815_netty.activeObject.activeobject;

import org.saxing.multithread.a20180815_netty.activeObject.ActivationQueue;
import org.saxing.multithread.a20180815_netty.activeObject.SchedulerThread;

public class ActiveObjectFactory {

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread scheduler = new SchedulerThread(queue);
        Proxy proxy = new Proxy(scheduler, servant);
        scheduler.start();
        return proxy;
    }

}
