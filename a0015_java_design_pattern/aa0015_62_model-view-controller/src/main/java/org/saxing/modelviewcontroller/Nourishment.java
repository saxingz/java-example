package org.saxing.modelviewcontroller;

/**
 * Nourishment enumeration
 *
 * @author saxing 2019/4/21 14:19
 */
public enum Nourishment {

    SATURATED("saturated"), HUNGRY("hungry"), STARVING("starving");

    private String title;

    Nourishment(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
