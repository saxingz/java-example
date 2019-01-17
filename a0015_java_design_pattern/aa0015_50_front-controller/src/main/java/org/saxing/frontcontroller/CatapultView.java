package org.saxing.frontcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View for catapults.
 *
 * @author saxing 2019/1/17 22:53
 */
public class CatapultView implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatapultView.class);

    @Override
    public void display() {
        LOGGER.info("Displaying catapults");
    }
}
