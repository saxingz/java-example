package org.saxing.study.a20180814_handler.activeobject_concurrent;

public class ActiveObjectFactory {

    public static ActiveObject createActiveObject(){
        return new ActiveObjectImpl();
    }

}
