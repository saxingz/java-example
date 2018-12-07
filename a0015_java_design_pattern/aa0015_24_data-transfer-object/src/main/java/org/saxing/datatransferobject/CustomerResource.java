package org.saxing.datatransferobject;

import java.util.List;

/**
 * CustomerResource
 *
 * @author saxing 2018/12/7 22:46
 */
public class CustomerResource {

    private List<CustomerDto> customerDtoList;

    public CustomerResource(List<CustomerDto> customerDtoList) {
        this.customerDtoList = customerDtoList;
    }

    public List<CustomerDto> getAll(){
        return customerDtoList;
    }

    public void save(CustomerDto customerDto){
        customerDtoList.add(customerDto);
    }

    public void delete(String customerId){
        customerDtoList.removeIf(customer -> customer.getId().equals(customerId));
    }

}
