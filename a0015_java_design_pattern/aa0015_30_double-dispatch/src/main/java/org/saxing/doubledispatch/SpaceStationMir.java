package org.saxing.doubledispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SpaceStationMir 
 * 
 * @author saxing 2018/12/16 13:47
 */
public class SpaceStationMir extends GameObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceStationMir.class);

    public SpaceStationMir(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    @Override
    public void collision(GameObject gameObject) {
        gameObject.collisionResolve(this);
    }

    @Override
    public void collisionResolve(FlamingAsteroid asteroid) {
        LOGGER.info("{} hits {}. {} is damaged! {} is set on fire!", asteroid.getClass().getSimpleName(),
                this.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getClass().getSimpleName());
        setDamaged(true);
        setOnFire(true);
    }

    @Override
    public void collisionResolve(Meteoroid meteoroid) {
        LOGGER.info("{} hits {}. {} is damaged!", meteoroid.getClass().getSimpleName(),
                this.getClass().getSimpleName(), this.getClass().getSimpleName());
        setDamaged(true);
    }

    @Override
    public void collisionResolve(SpaceStationMir mir) {
        LOGGER.info("{} hits {}. {} is damaged!", mir.getClass().getSimpleName(),
                this.getClass().getSimpleName(), this.getClass().getSimpleName());
        setDamaged(true);
    }

    @Override
    public void collisionResolve(SpaceStationIss iss) {
        LOGGER.info("{} hits {}. {} is damaged!", iss.getClass().getSimpleName(),
                this.getClass().getSimpleName(), this.getClass().getSimpleName());
        setDamaged(true);
    }
}
