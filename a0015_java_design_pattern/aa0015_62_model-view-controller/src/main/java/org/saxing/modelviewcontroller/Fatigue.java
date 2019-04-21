package org.saxing.modelviewcontroller;

/**
 * Fatigue enumeration
 *
 * @author saxing 2019/4/21 14:18
 */
public enum Fatigue {

    ALERT("alert"), TIRED("tired"), SLEEPING("sleeping");

    private String title;

    Fatigue(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
