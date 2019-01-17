package org.saxing.frontcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View for archers.
 *
 * @author saxing 2019/1/17 22:52
 */
public class ArcherView implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArcherView.class);

    @Override
    public void display() {
        LOGGER.info("Displaying archers");
    }
}
