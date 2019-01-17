package org.saxing.frontcontroller;

/**
 * Command for catapults.
 *
 * @author saxing 2019/1/17 22:56
 */
public class CatapultCommand implements Command {

    @Override
    public void process() {
        new CatapultView().display();
    }
}
