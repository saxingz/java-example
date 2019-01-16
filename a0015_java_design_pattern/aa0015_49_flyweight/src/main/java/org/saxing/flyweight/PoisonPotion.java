package org.saxing.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PoisonPotion
 *
 * @author saxing 2019/1/16 13:29
 */
public class PoisonPotion implements Potion {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoisonPotion.class);

    @Override
    public void drink() {
        LOGGER.info("Urgh! This is poisonous. (Potion={})", System.identityHashCode(this));
    }

}
