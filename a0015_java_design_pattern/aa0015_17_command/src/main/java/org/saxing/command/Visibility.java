package org.saxing.command;

/**
 * Visibility enum
 *
 * @author saxing  2018/11/23 14:21
 */
public enum Visibility {

    VISIBLE("visible"), INVISIBLE("invisible");

    private String title;

    Visibility(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
