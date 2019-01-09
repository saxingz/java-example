package org.saxing.factorymethod;

/**
 * WeaponType enumeration
 *
 * @author saxing 2019/1/9 23:31
 */
public enum WeaponType {
    SHORT_SWORD("short sword"), SPEAR("spear"), AXE("axe"), UNDEFINED("")
    ;

    private String title;

    WeaponType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
