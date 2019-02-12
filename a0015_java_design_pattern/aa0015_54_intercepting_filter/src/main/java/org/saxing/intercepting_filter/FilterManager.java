package org.saxing.intercepting_filter;

/**
 * Filter Manager manages the filters and {@link FilterChain}.
 *
 * @author saxing 2019/2/12 17:36
 */
public class FilterManager {

    private FilterChain filterChain;

    public FilterManager() {
        filterChain = new FilterChain();
    }

    public void addFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public String filterRequest(Order order) {
        return filterChain.execute(order);
    }

}
