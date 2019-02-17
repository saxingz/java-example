package org.saxing.aa0015_56_iterator.list;

/**
 * item
 *
 * @author saxing 2019/2/17 20:46
 */
public class Item {

    private ItemType type;
    private String name;

    public Item(ItemType type, String name) {
        this.setType(type);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public final void setType(ItemType type) {
        this.type = type;
    }

}
