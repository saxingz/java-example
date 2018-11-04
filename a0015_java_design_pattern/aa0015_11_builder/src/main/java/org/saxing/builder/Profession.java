package org.saxing.builder;

/**
 * Profession
 *
 * @author saxing  2018/11/4 10:57
 */
public enum  Profession {

    WARRIOR, THIEF, MAGE, PRIEST;


    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
