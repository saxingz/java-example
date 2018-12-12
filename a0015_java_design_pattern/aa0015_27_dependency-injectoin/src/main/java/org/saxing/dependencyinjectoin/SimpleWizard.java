package org.saxing.dependencyinjectoin;

/**
 * Naive Wizard implementation violating the inversion of control principle. It should depend on
 * abstraction instead.
 *
 * @author saxing 2018/12/12 23:10
 */
public class SimpleWizard implements Wizard {

    private OldTobyTobacco tobacco = new OldTobyTobacco();

    @Override
    public void smoke() {
        tobacco.smoke(this);
    }
}
