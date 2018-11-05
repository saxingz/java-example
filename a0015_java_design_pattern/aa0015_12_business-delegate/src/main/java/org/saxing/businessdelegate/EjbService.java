package org.saxing.businessdelegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ejb service
 *
 * @author saxing  2018/11/5 21:37
 */
public class EjbService implements BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EjbService.class);

    @Override
    public void doProcessing() {
        LOGGER.info("EjbService is now processing");
    }
}
