package org.saxing.ambassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * remote service
 *
 * @author saxing  2018/10/14 14:24
 */
public class RemoteService implements RemoteServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteService.class);
    private static RemoteService service = null;

    static synchronized RemoteService getRemoteService(){
        if (service == null){
            service = new RemoteService();
        }
        return service;
    }

    private RemoteService(){}

    /**
     * Remote function takes a value and multiplies it by 10 taking a random amount of time.
     * Will sometimes return -1. This imitates connectivity issues a client might have to account for.
     * @param value integer value to be multiplied.
     * @return if waitTime is more than 200ms, it returns value * 10, otherwise -1.
     */
    @Override
    public long doRemoteFunction(int value){
        long waitTime = (long) Math.floor(Math.random() * 1000);

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            LOGGER.error("Thread sleep state interrupted.", e);
        }
        return waitTime >= 200 ? value * 10 : -1;
    }
}
