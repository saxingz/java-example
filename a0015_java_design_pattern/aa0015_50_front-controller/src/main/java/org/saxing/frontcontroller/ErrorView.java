package org.saxing.frontcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View for errors.
 *
 * @author saxing 2019/1/17 22:51
 */
public class ErrorView implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorView.class);

    @Override
    public void display() {
        LOGGER.error("Error 500");
    }
}
