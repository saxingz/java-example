package org.saxing.frontcontroller;

public class UnknownCommand implements Command {

    @Override
    public void process() {
        new ErrorView().display();
    }
}
