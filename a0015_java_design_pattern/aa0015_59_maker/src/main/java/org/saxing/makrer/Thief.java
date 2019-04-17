package org.saxing.makrer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * thief
 *
 * @author saxing 2019/4/16 22:36
 */
public class Thief {

    private static final Logger LOGGER = LoggerFactory.getLogger(Thief.class);

    protected static void steal() {
        LOGGER.info("Steal valuable items");
    }

    protected static void doNothing() {
        LOGGER.info("Pretend nothing happened and just leave");
    }

}
