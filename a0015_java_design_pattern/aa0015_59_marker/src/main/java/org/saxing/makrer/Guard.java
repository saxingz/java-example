package org.saxing.makrer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class defining Guard
 *
 * @author saxing 2019/4/17 20:58
 */
public class Guard implements Permission  {

    private static final Logger LOGGER = LoggerFactory.getLogger(Guard.class);

    protected static void enter() {

        LOGGER.info("You can enter");
    }

}
