package org.saxing.builder;

/**
 * Weapon
 *
 * @author saxing  2018/11/4 10:58
 */
public enum Weapon {

    DAGGER, SWORD, AXE, WARHAMMER, BOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
