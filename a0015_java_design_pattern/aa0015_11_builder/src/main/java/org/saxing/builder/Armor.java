package org.saxing.builder;

/**
 * Armor
 *
 * @author saxing  2018/11/4 10:58
 */
public enum Armor {

    CLOTHES("clothes"), LEATHER("leather"), CHAIN_MAIL("chain mail"), PLATE_MAIL("plate mail");

    private String title;

    Armor(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return title;
    }
}
