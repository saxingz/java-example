package org.saxing.doubledispatch;

public class GameObject extends Rectangle {

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
}
