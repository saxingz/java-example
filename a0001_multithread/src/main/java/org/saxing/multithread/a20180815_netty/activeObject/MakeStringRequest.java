package org.saxing.multithread.a20180815_netty.activeObject;

import org.saxing.multithread.a20180815_netty.activeObject.activeobject.Servant;
import org.saxing.multithread.a20180815_netty.activeObject.result.FutureResult;
import org.saxing.multithread.a20180815_netty.activeObject.result.Result;

public class MakeStringRequest extends MethodRequest<String> {

    private final int count;
    private final char fillchar;

    public MakeStringRequest(Servant servant, FutureResult<String> future, int count, char fillchar) {
        super(servant, future);
        this.count = count;
        this.fillchar = fillchar;
    }

    @Override
    public void execute() {
        Result<String> result = servant.makeString(count, fillchar);
        future.setResult(result);
    }
}
