package org.saxing.businessdelegate;

/**
 * client
 *
 * @author saxing  2018/11/5 22:04
 */
public class Client {

    private BusinessDelegate businessDelegate;

    public Client(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    public void doTask(){
        businessDelegate.doTask();
    }

}
