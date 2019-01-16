package org.saxing.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * healing potion
 *
 * @author saxing 2019/1/16 13:31
 */
public class HealingPotion implements Potion {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealingPotion.class);

    @Override
    public void drink() {
        LOGGER.info("You feel healed. (Potion={})", System.identityHashCode(this));
    }

}
