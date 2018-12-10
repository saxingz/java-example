package org.saxing.delegation.printers;

import org.saxing.delegation.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * epson printer
 *
 * @author saxing 2018/12/10 22:23
 */
public class EpsonPrinter implements Printer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EpsonPrinter.class);

    @Override
    public void print(String message) {
        LOGGER.info("Epson Printer : {}", message);
    }
}
