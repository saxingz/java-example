package org.saxing.businessdelegate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * main
 *
 * @author saxing  2018/11/5 22:05
 */
//@SpringBootApplication
public class Aa001512BusinessDelegateApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001512BusinessDelegateApplication.class, args);
//    }

    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        BusinessLookup businessLookup = new BusinessLookup();
        businessLookup.setEjbService(new EjbService());
        businessLookup.setJmsService(new JmsService());

        businessDelegate.setBusinessLookup(businessLookup);
        businessDelegate.setServiceType(ServiceType.EJB);

        Client client = new Client(businessDelegate);
        client.doTask();

        businessDelegate.setServiceType(ServiceType.JMS);
        client.doTask();
    }
}
