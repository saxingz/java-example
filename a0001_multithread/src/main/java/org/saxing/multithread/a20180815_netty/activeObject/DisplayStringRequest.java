package org.saxing.multithread.a20180815_netty.activeObject;

import org.saxing.multithread.a20180815_netty.activeObject.activeobject.Servant;

public class DisplayStringRequest extends MethodRequest<String> {

    private final String string;

    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }

    @Override
    public void execute() {
        servant.displayString(string);
    }
}
