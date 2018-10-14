package org.saxing.ambassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * client 
 * 
 * @author saxing  2018/10/14 15:01
 */
public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Thread.currentThread().getClass());
    private final ServiceAmbassador serviceAmbassador = new ServiceAmbassador();

    long useService(int value){
        long result = serviceAmbassador.doRemoteFunction(value);
        LOGGER.info("Service result: " + result);
        return result;
    }
}
