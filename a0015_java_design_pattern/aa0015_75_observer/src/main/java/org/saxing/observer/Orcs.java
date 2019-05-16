package org.saxing.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Orcs
 *
 * @author saxing 2019/5/16 14:30
 */
public class Orcs implements WeatherObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Orcs.class);

    @Override
    public void update(WeatherType currentWeather) {
        switch (currentWeather) {
            case COLD:
                LOGGER.info("The orcs are freezing cold.");
                break;
            case RAINY:
                LOGGER.info("The orcs are dripping wet.");
                break;
            case SUNNY:
                LOGGER.info("The sun hurts the orcs' eyes.");
                break;
            case WINDY:
                LOGGER.info("The orc smell almost vanishes in the wind.");
                break;
            default:
                break;
        }
    }
}
