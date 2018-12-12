package org.saxing.dependencyinjectoin;

/**
 * AdvancedSorceress implements inversion of control. It depends on abstraction that can be injected
 * through its setter.
 * 
 * @author saxing 2018/12/11 9:43
 */
public class AdvancedSorceress implements Wizard {

    private Tobacco tobacco;

    public AdvancedSorceress(Tobacco tobacco) {
        this.tobacco = tobacco;
    }

    @Override
    public void smoke() {
        tobacco.smoke(this);
    }
}
