package org.saxing.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * InvisibilityPotion
 *
 * @author saxing 2019/1/16 13:30
 */
public class InvisibilityPotion implements Potion {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvisibilityPotion.class);

    @Override
    public void drink() {
        LOGGER.info("You become invisible. (Potion={})", System.identityHashCode(this));
    }

}
