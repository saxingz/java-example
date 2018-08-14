package org.saxing.study.a20180814_handler.activeobject;

public interface ActiveObject {

    public abstract Result<String> makeString(int count, char fillchar);

    public abstract void displayString(String string);

}
