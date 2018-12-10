package org.saxing.delegation.printers;

import org.saxing.delegation.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * canon printer
 *
 * @author saxing 2018/12/10 22:23
 */
public class CanonPrinter implements Printer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanonPrinter.class);

    @Override
    public void print(String message) {
        LOGGER.info("Canon Printer : {}", message);
    }
}
