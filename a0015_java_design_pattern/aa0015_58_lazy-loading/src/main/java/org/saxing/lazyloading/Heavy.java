package org.saxing.lazyloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * heavy object
 *
 * @author saxing 2019/4/14 13:57
 */
public class Heavy {

    private static final Logger LOGGER = LoggerFactory.getLogger(Heavy.class);

    /**
     * Constructor
     */
    public Heavy() {
        LOGGER.info("Creating Heavy ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("Exception caught.", e);
        }
        LOGGER.info("... Heavy created");
    }

}
