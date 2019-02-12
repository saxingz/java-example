package org.saxing.intercepting_filter;

/**
 * Base class for order processing filters. Handles chain management.
 *
 * @author saxing 2019/2/12 15:29
 */
public abstract class AbstractFilter implements Filter {

    private Filter next;

    public AbstractFilter() {}

    public AbstractFilter(Filter next) {
        this.next = next;
    }

    @Override
    public String execute(Order order) {
        if (getNext() != null){
            return getNext().execute(order);
        }else{
            return "";
        }
    }

    @Override
    public void setNext(Filter filter) {
        this.next = filter;
    }

    @Override
    public Filter getNext() {
        return next;
    }

    @Override
    public Filter getLast() {
        Filter last = this;
        while (last.getNext() != null){
            last = last.getNext();
        }
        return last;
    }
}
