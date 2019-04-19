package org.saxing.memento;

/**
 * Star uses "mementos" to store and restore state.
 *
 * @author saxing 2019/4/19 16:40
 */
public class Star {

    private StarType type;
    private int ageYears;
    private int massTons;

    /**
     * Constructor
     */
    public Star(StarType startType, int startAge, int startMass) {
        this.type = startType;
        this.ageYears = startAge;
        this.massTons = startMass;
    }




}
