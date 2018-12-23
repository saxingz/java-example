package org.saxing.eventaggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * KingJoffrey observes events from {@link KingsHand}.
 * 
 * @author saxing 2018/12/23 17:36
 */
public class KingJoffrey implements EventObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(KingJoffrey.class);

    @Override
    public void onEvent(Event e) {
        LOGGER.info("Received event from the King's Hand: {}", e.toString());
    }
}
