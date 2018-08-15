package org.saxing.a001_multithread.a20180815_netty.activeObject.activeobject;

import org.saxing.a001_multithread.a20180815_netty.activeObject.result.Result;

public interface ActiveObject {

    public abstract Result<String> makeString(int count, char filter);

    public abstract void displayString(String string);

}
