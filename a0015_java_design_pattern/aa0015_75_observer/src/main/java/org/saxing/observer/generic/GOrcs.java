package org.saxing.observer.generic;

import org.saxing.observer.WeatherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.saxing.observer.WeatherType.*;

/**
 * GOrcs
 *
 * @author saxing 2019/5/16 15:03
 */
public class GOrcs implements Race  {

    private static final Logger LOGGER = LoggerFactory.getLogger(GOrcs.class);

    @Override
    public void update(GWeather subject, WeatherType weatherType) {
        switch (weatherType) {
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
