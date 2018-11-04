package org.saxing.builder;

/**
 * HairType
 *
 * @author saxing  2018/11/4 11:00
 */
public enum  HairType {

    BALD("bald"), SHORT("short"), CURLY("curly"), LONG_STRAIGHT("long straight"), LONG_CURLY(
            "long curly");

    private String title;

    HairType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
