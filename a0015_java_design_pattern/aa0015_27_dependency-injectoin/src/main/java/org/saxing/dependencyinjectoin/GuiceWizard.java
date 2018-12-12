package org.saxing.dependencyinjectoin;

import com.google.inject.Inject;

/**
 * GuiceWizard implements inversion of control. Its dependencies are injected through its
 * constructor by Guice framework.
 * 
 * @author saxing 2018/12/12 23:08
 */
public class GuiceWizard implements Wizard {

    private Tobacco tobacco;

    @Inject
    public GuiceWizard(Tobacco tobacco) {
        this.tobacco = tobacco;
    }

    @Override
    public void smoke() {
        tobacco.smoke(this);
    }
}
