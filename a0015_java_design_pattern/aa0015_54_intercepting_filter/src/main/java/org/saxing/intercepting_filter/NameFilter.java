package org.saxing.intercepting_filter;

/**
 * Concrete implementation of filter. This filter checks if the input in the Name field is valid.
 * (alphanumeric)
 *
 * @author saxing 2019/2/12 17:28
 */
public class NameFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        if (order.getName() == null || order.getName().isEmpty()
                || order.getName().matches(".*[^\\w|\\s]+.*")) {
            return result + "Invalid name! ";
        } else {
            return result;
        }
    }
}
