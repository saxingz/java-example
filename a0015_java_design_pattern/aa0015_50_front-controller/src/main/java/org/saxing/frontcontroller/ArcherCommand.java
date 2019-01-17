package org.saxing.frontcontroller;

/**
 * Command for archers.
 *
 * @author saxing 2019/1/17 22:55
 */
public class ArcherCommand implements Command {

    @Override
    public void process() {
        new ArcherView().display();
    }
}
