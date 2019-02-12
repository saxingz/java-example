package org.saxing.intercepting_filter;

/**
 * main
 *
 * @author saxing 2019/2/12 17:48
 */
public class Aa001554InterceptingFilterApplication {

    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager();
        filterManager.addFilter(new NameFilter());
        filterManager.addFilter(new ContactFilter());
        filterManager.addFilter(new AddressFilter());
        filterManager.addFilter(new DepositFilter());
        filterManager.addFilter(new OrderFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
    }

}

