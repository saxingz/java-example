package org.saxing.doubledispatch;

/**
 * GameObject
 * 
 * @author saxing 2018/12/16 13:38
 */
public abstract class GameObject extends Rectangle {

    private boolean damaged;
    private boolean onFire;

    public GameObject(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isOnFire() {
        return onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public abstract void collision(GameObject gameObject);

    public abstract void collisionResolve(FlamingAsteroid asteroid);

    public abstract void collisionResolve(Meteoroid meteoroid);

    public abstract void collisionResolve(SpaceStationMir mir);

    public abstract void collisionResolve(SpaceStationIss iss);
}
