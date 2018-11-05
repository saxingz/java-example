package org.saxing.businessdelegate;

import java.util.Objects;

/**
 * BusinessLookup
 *
 * @author saxing  2018/11/5 22:00
 */
public class BusinessLookup {

    private EjbService ejbService;

    private JmsService jmsService;

    public BusinessService getBussiness(ServiceType serviceType){
        if (Objects.equals(serviceType, ServiceType.EJB)){
            return ejbService;
        } else{
            return jmsService;
        }
    }

    public void setEjbService(EjbService ejbService) {
        this.ejbService = ejbService;
    }

    public void setJmsService(JmsService jmsService) {
        this.jmsService = jmsService;
    }
}
