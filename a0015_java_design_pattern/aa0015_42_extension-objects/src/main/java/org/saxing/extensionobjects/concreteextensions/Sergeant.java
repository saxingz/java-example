package org.saxing.extensionobjects.concreteextensions;

import org.saxing.extensionobjects.abstractextensions.SergeantExtension;
import org.saxing.extensionobjects.units.SergeantUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class defining Sergeant
 *
 * @author saxing 2019/1/6 11:50
 */
public class Sergeant implements SergeantExtension {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sergeant.class);

    private SergeantUnit unit;

    public Sergeant(SergeantUnit unit) {
        this.unit = unit;
    }

    @Override
    public void sergeantReady() {
        LOGGER.info("[Sergeant] " + unit.getName() + " is ready! ");
    }
}
