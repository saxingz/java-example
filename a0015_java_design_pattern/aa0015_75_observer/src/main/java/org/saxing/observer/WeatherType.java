package org.saxing.observer;

/**
 * WeatherType enumeration
 *
 * @author saxing 2019/5/16 14:28
 */
public enum WeatherType {

    SUNNY, RAINY, WINDY, COLD;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

