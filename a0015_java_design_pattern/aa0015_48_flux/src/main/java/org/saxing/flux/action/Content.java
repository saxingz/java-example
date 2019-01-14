package org.saxing.flux.action;

/**
 * Content items.
 *
 * @author saxing 2019/1/14 23:12
 */
public enum Content {

    PRODUCTS("Products - This page lists the company's products."),
    COMPANY("Company - This page displays information about the company.");

    private String title;

    private Content(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
