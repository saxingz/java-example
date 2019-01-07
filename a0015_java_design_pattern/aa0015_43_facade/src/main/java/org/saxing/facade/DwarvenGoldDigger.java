package org.saxing.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DwarvenGoldDigger is one of the goldmine subsystems.
 *
 * @author saxing 2019/1/7 9:05
 */
public class DwarvenGoldDigger extends DwarvenMineWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwarvenGoldDigger.class);

    @Override
    public void work() {
        LOGGER.info("{} digs for gold.", name());
    }

    @Override
    public String name() {
        return "Dwarf gold digger";
    }
}
