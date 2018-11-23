package org.saxing.command;

/**
 * Enumeration for target size.
 *
 * @author saxing  2018/11/23 14:24
 */
public enum  Size {

    SMALL("small"), NORMAL("normal");

    private String title;

    Size(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
