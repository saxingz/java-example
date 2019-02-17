package org.saxing.aa0015_56_iterator.list;

import org.saxing.aa0015_56_iterator.Iterator;

import java.util.List;

/**
 *
 * TreasureChestItemIterator
 *
 * @author saxing 2019/2/17 20:48
 */
public class TreasureChestItemIterator implements Iterator<Item> {

    private TreasureChest chest;
    private int idx;
    private ItemType type;

    /**
     * Constructor
     */
    public TreasureChestItemIterator(TreasureChest chest, ItemType type) {
        this.chest = chest;
        this.type = type;
        this.idx = -1;
    }

    @Override
    public boolean hasNext() {
        return findNextIdx() != -1;
    }

    @Override
    public Item next() {
        idx = findNextIdx();
        if (idx != -1) {
            return chest.getItems().get(idx);
        }
        return null;
    }

    private int findNextIdx(){
        List<Item> items = chest.getItems();
        boolean found = false;
        int tempIdx = idx;
        while (!found){
            tempIdx ++;
            if (tempIdx >= items.size()){
                tempIdx = -1;
                break;
            }
            if (type.equals(ItemType.ANY) || items.get(tempIdx).getType().equals(type)){
                break;
            }
        }
        return tempIdx;
    }

}
