package org.saxing.multithread.a20180815_netty.activeObject.activeobject;

import org.saxing.multithread.a20180815_netty.activeObject.result.Result;

public interface ActiveObject {

    public abstract Result<String> makeString(int count, char filter);

    public abstract void displayString(String string);

}
