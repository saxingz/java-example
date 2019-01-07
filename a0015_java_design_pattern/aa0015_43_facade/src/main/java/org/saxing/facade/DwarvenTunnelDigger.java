package org.saxing.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DwarvenTunnelDigger is one of the goldmine subsystems.
 *
 * @author saxing 2019/1/7 9:06
 */
public class DwarvenTunnelDigger extends DwarvenMineWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwarvenTunnelDigger.class);

    @Override
    public void work() {
        LOGGER.info("{} creates another promising tunnel.", name());
    }

    @Override
    public String name() {
        return "Dwarven tunnel digger";
    }
}
