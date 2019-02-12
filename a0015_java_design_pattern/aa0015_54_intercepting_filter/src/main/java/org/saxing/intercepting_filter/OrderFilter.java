package org.saxing.intercepting_filter;

/**
 * Concrete implementation of filter. This checks for the order field.
 *
 * @author saxing 2019/2/12 17:28
 */
public class OrderFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        if (order.getOrderItem() == null || order.getOrderItem().isEmpty()) {
            return result + "Invalid order! ";
        } else {
            return result;
        }
    }

}
