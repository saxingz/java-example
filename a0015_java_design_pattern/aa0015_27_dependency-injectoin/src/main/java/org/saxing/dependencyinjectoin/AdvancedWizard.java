package org.saxing.dependencyinjectoin;

/**
 * AdvancedWizard implements inversion of control. It depends on abstraction that can be injected
 * through its constructor.
 *
 * @author saxing 2018/12/12 23:07
 */
public class AdvancedWizard implements Wizard {

    private Tobacco tobacco;

    public AdvancedWizard(Tobacco tobacco) {
        this.tobacco = tobacco;
    }

    @Override
    public void smoke() {
        tobacco.smoke(this);
    }
}
