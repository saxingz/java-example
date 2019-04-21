package org.saxing.modelviewcontroller;

/**
 * Health enumeration
 *
 * @author saxing 2019/4/21 14:19
 */
public enum Health {

    HEALTHY("healthy"), WOUNDED("wounded"), DEAD("dead");

    private String title;

    Health(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}

