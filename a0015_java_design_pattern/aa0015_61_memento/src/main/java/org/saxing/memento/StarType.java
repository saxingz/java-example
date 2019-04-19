package org.saxing.memento;

/**
 * star type
 *
 * @author saxing 2019/4/19 16:39
 */
public enum StarType {

    SUN("sun"), RED_GIANT("red giant"), WHITE_DWARF("white dwarf"), SUPERNOVA("supernova"), DEAD(
            "dead star"), UNDEFINED("");

    private String title;

    StarType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
