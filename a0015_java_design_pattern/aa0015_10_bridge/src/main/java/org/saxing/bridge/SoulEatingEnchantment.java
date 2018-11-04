package org.saxing.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * soul eating enchantment
 * 
 * @author saxing  2018/11/3 17:33 
 */
public class SoulEatingEnchantment implements Enchantment {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoulEatingEnchantment.class);

    @Override
    public void onActive() {
        LOGGER.info("The item spreads bloodlust.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item eats the soul of enemies.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("Bloodlust slowly disappears.");
    }
}
