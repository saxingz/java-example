package org.saxing.adapter;

/**
 * captain
 *
 * @author saxing  2018/10/9 22:04
 */
public class Captain {

    private RowingBoat rowingBoat;

    public Captain(){

    }

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void setRowingBoat(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void row(){
        rowingBoat.row();
    }
}
