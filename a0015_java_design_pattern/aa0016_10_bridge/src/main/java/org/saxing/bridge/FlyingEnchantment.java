package org.saxing.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * flying enchantment
 * 
 * @author saxing  2018/11/3 17:31  
 */
public class FlyingEnchantment implements Enchantment {

    private Logger LOGGER = LoggerFactory.getLogger(FlyingEnchantment.class);

    @Override
    public void onActive() {
        LOGGER.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("The item's glow fades.");
    }
}
