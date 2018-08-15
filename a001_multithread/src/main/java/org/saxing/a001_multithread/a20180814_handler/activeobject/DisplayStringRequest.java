package org.saxing.a001_multithread.a20180814_handler.activeobject;

public class DisplayStringRequest extends MethodRequest<Object> {

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
