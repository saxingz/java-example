package org.saxing.doubledispatch;

/**
 * FlamingAsteroid
 * 
 * @author saxing 2018/12/16 13:46
 */
public class FlamingAsteroid extends Meteoroid {

    public FlamingAsteroid(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
        setOnFire(true);
    }

    @Override
    public void collision(GameObject gameObject) {
        gameObject.collisionResolve(this);
    }
}
