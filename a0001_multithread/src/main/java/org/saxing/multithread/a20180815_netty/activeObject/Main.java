package org.saxing.multithread.a20180815_netty.activeObject;

import org.saxing.multithread.a20180815_netty.activeObject.activeobject.ActiveObject;
import org.saxing.multithread.a20180815_netty.activeObject.activeobject.ActiveObjectFactory;
import org.saxing.multithread.a20180815_netty.activeObject.client.DisplayClientThread;
import org.saxing.multithread.a20180815_netty.activeObject.client.MakeClientThread;

public class Main {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakeClientThread("Alice", activeObject).start();
        new MakeClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }

}
