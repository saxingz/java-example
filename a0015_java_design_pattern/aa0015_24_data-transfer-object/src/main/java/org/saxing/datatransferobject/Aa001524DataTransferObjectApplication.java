package org.saxing.datatransferobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * main
 *
 * @author saxing 2018/12/7 23:09
 */
//@SpringBootApplication
public class Aa001524DataTransferObjectApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001524DataTransferObjectApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(Aa001524DataTransferObjectApplication.class, args);

        List<CustomerDto> customers = new ArrayList<>();
        CustomerDto customerOne = new CustomerDto("1", "Kelly", "Brown");
        CustomerDto customerTwo = new CustomerDto("2", "Alfonso", "Bass");

        customers.add(customerOne);
        customers.add(customerTwo);

        CustomerResource customerResource = new CustomerResource(customers);

        LOGGER.info("All customers:-");
        List<CustomerDto> allCustomers = customerResource.getAll();
        printCustomerDetails(allCustomers);

        LOGGER.info("----------------------------------------------------------");

        LOGGER.info("Deleting customer with id {1}");
        customerResource.delete(customerOne.getId());
        allCustomers = customerResource.getAll();
        printCustomerDetails(allCustomers);

        LOGGER.info("----------------------------------------------------------");

        LOGGER.info("Adding customer three}");
        CustomerDto customerThree = new CustomerDto("3", "Lynda", "Blair");
        customerResource.save(customerThree);
        allCustomers = customerResource.getAll();
        printCustomerDetails(allCustomers);



    }

    private static void printCustomerDetails(List<CustomerDto> allCustomers) {
        allCustomers.forEach(customer -> LOGGER.info(customer.getFirstName()));
    }
}
