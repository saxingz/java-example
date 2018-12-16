package org.saxing.doubledispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Meteoroid game object
 *
 * @author saxing 2018/12/16 13:38
 */
public class Meteoroid extends GameObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(Meteoroid.class);

    public Meteoroid(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    @Override
    public void collision(GameObject gameObject) {
        gameObject.collisionResolve(this);
    }

    @Override
    public void collisionResolve(FlamingAsteroid asteroid) {
        LOGGER.info("{} hits {}.", asteroid.getClass().getSimpleName(), this.getClass().getSimpleName());
    }

    @Override
    public void collisionResolve(Meteoroid meteoroid) {
        LOGGER.info("{} hits {}.", meteoroid.getClass().getSimpleName(), this.getClass().getSimpleName());
    }

    @Override
    public void collisionResolve(SpaceStationMir mir) {
        LOGGER.info("{} hits {}.", mir.getClass().getSimpleName(), this.getClass().getSimpleName());
    }

    @Override
    public void collisionResolve(SpaceStationIss iss) {
        LOGGER.info("{} hits {}.", iss.getClass().getSimpleName(), this.getClass().getSimpleName());
    }


}
