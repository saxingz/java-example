package org.saxing.intercepting_filter;

/**
 * Filters perform certain tasks prior or after execution of request by request handler. In this
 * case, before the request is handled by the target, the request undergoes through each Filter
 *
 * @author saxing 2019/2/12 15:24
 */
public interface Filter {

    /**
     * Execute order processing filter.
     */
    String execute(Order order);

    /**
     * Set next filter in chain after this.
     */
    void setNext(Filter filter);

    /**
     * Get next filter in chain after this.
     */
    Filter getNext();

    /**
     * Get last filter in the chain.
     */
    Filter getLast();

}
