package org.saxing.builder;

/**
 * HairColor
 *
 * @author saxing  2018/11/4 10:59
 */
public enum HairColor {

    WHITE, BLOND, RED, BROWN, BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
