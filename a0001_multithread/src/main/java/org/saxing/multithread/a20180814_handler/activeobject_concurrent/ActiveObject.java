package org.saxing.multithread.a20180814_handler.activeobject_concurrent;

import java.util.concurrent.Future;

public interface ActiveObject {

    public abstract Future<String> makeString(int count, char fillchar);
    public abstract void displayString(String string);
    public abstract void shutdown();

}
