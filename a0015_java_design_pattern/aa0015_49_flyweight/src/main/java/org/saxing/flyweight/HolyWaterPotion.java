package org.saxing.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HolyWaterPotion
 *
 * @author saxing 2019/1/16 13:31
 */
public class HolyWaterPotion implements Potion {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolyWaterPotion.class);

    @Override
    public void drink() {
        LOGGER.info("You feel blessed. (Potion={})", System.identityHashCode(this));
    }

}
