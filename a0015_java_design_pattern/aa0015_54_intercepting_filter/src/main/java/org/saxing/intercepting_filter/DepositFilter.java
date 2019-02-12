package org.saxing.intercepting_filter;

/**
 * Concrete implementation of filter This checks for the deposit code
 *
 * @author saxing 2019/2/12 17:25
 */
public class DepositFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        if (order.getDepositNumber() == null || order.getDepositNumber().isEmpty()) {
            return result + "Invalid deposit number! ";
        } else {
            return result;
        }
    }
}
