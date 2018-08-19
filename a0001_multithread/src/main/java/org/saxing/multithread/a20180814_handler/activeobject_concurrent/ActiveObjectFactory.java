package org.saxing.multithread.a20180814_handler.activeobject_concurrent;

public class ActiveObjectFactory {

    public static ActiveObject createActiveObject(){
        return new ActiveObjectImpl();
    }

}
