package org.saxing.multithread.a20180815_netty.activeObject.result;

public class RealResult<T> extends Result<T> {

    private final T resultValue;

    public RealResult(T resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public T getResultValue() {
        return resultValue;
    }
}
