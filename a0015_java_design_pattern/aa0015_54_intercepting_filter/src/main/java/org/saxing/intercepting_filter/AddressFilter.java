package org.saxing.intercepting_filter;

/**
 *
 * Concrete implementation of filter This filter is responsible for checking/filtering the input in
 * the address field.
 *
 * @author saxing 2019/2/12 17:21
 *
 */
public class AddressFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            return result + "Invalid address! ";
        } else {
            return result;
        }
    }
}
