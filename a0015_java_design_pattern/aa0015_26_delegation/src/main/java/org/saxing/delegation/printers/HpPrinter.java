package org.saxing.delegation.printers;

import org.saxing.delegation.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hp printer
 *
 * @author saxing 2018/12/10 22:24
 */
public class HpPrinter implements Printer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HpPrinter.class);

    @Override
    public void print(String message) {
        LOGGER.info("HP Printer : {}", message);
    }
}
