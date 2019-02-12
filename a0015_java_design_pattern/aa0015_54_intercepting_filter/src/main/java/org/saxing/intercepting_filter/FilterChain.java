package org.saxing.intercepting_filter;

/**
 * Filter Chain carries multiple filters and help to execute them in defined order on target.
 *
 * @author saxing 2019/2/12 17:30
 */
public class FilterChain {

    private Filter chain;

    public void addFilter(Filter filter){
        if (chain == null){
            chain = filter;
        }else{
            chain.getLast().setNext(filter);
        }
    }

    public String execute(Order order){
        if (chain != null) {
            return chain.execute(order);
        } else {
            return "RUNNING...";
        }
    }

}
