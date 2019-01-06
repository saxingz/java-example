package org.saxing.extensionobjects.concreteextensions;

import org.saxing.extensionobjects.abstractextensions.CommanderExtension;
import org.saxing.extensionobjects.units.CommanderUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class defining Commander
 *
 * @author saxing 2019/1/6 11:54
 */
public class Commander implements CommanderExtension {

    private static final Logger LOGGER = LoggerFactory.getLogger(Commander.class);

    private CommanderUnit unit;

    public Commander(CommanderUnit unit) {
        this.unit = unit;
    }

    @Override
    public void commanderReady() {
        LOGGER.info("[Commander] " + unit.getName() + " is ready!");
    }
}
