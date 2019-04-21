package org.saxing.modelviewcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GiantView displays the giant
 *
 * @author saxing 2019/4/21 14:20
 */
public class GiantView {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiantView.class);

    public void displayGiant(GiantModel giant) {
        LOGGER.info(giant.toString());
    }

}
