package org.saxing.adapter;

/**
 * adapter
 *
 * @author saxing  2018/10/9 22:07
 */
public class FishingBoatAdapter implements RowingBoat {

    private FishingBoat boat;

    public FishingBoatAdapter() {
        boat = new FishingBoat();
    }

    @Override
    public void row() {
        boat.sail();
    }
}
