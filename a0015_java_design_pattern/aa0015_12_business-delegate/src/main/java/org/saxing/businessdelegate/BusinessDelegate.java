package org.saxing.businessdelegate;

/**
 * BusinessDelegate separates the presentation and business tiers
 *
 * @author saxing  2018/11/5 22:03
 */
public class BusinessDelegate {

    private BusinessLookup businessLookup;
    private BusinessService businessService;
    private ServiceType serviceType;

    public void setBusinessLookup(BusinessLookup businessLookup) {
        this.businessLookup = businessLookup;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public void doTask(){
        businessService = businessLookup.getBussiness(serviceType);
        businessService.doProcessing();
    }

}
