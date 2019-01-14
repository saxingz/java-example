package org.saxing.flux.action;

/**
 * Menu items.
 *
 * @author saxing 2019/1/14 23:13
 */
public enum MenuItem {

    HOME("Home"), PRODUCTS("Products"), COMPANY("Company");

    private String title;

    MenuItem(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
