package org.saxing.extensionobjects.concreteextensions;

import org.saxing.extensionobjects.abstractextensions.SoldierExtension;
import org.saxing.extensionobjects.units.SoldierUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class defining Soldier
 *
 * @author saxing 2019/1/6 11:36
 */
public class Soldier implements SoldierExtension {

    private static final Logger LOGGER = LoggerFactory.getLogger(Soldier.class);

    private SoldierUnit unit;

    public Soldier(SoldierUnit unit) {
        this.unit = unit;
    }

    @Override
    public void soldierReady() {
        LOGGER.info("[Solider] " + unit.getName() + "  is ready!");
    }

}
