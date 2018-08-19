package org.saxing.multithread.a20180815_netty.activeObject_concurrent;

public class ActiveObjectFactory {

    public static ActiveObject createActiveObjectFactory(){
        return new ActiveObjectImpl();
    }

}
